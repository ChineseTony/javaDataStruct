package com.tom.protobuf;

import com.tom.protobuf.handler.NettyProtobufClientHanlder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangTao
 */
public class ProtobufClient {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    private static final int TRY_COUNT = 5;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ProtobufClient.doConnect(bootstrap,eventLoopGroup);
    }

    public static void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup){
        if (TRY_COUNT == atomicInteger.incrementAndGet()){
            System.out.println("重试"+(TRY_COUNT -1)+"次后，关闭");
            eventLoopGroup.shutdownGracefully();
            return;
        }
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(0,
                                    5, 0, TimeUnit.SECONDS));
                            p.addLast(new ProtobufVarint32FrameDecoder());
                            p.addLast(new ProtobufDecoder(UserInfo.UserMsg.getDefaultInstance()));
                            p.addLast(new ProtobufVarint32LengthFieldPrepender());
                            p.addLast(new ProtobufEncoder());
                            p.addLast(new NettyProtobufClientHanlder());
                        }
                    });

                ChannelFuture future = bootstrap.connect("localhost",
                        ProtobufServer.PORT)
                        .addListener(
                                (ChannelFuture futureListener)  -> {
                            final EventLoop eventLoop = futureListener.channel().eventLoop();
                            boolean initFlag = futureListener.isSuccess();
                            if (!initFlag) {
                                System.out.println("与服务端断开连接!在5s之后准备尝试重连!");
                                eventLoop.schedule(() ->
                                        doConnect(new Bootstrap(), eventLoop), 5, TimeUnit.SECONDS);
                            }else{
                                System.out.println("客户端启动成功");
                            }
                        }
                );
                future.channel().closeFuture().sync();
        }catch (InterruptedException   e){
            e.printStackTrace();
        }
//        finally {
//            eventLoopGroup.shutdownGracefully();
//        }


    }
}
