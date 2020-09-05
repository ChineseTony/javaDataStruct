package com.tom.zhanbao;
import	java.util.logging.Filter;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @author WangTao
 */
public class TimerClient {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //解决粘包问题 \n\r解码
                            p.addLast(new LineBasedFrameDecoder(1024));

//                            自定义分隔符解码器
//                            ByteBuf delimiter = Unpooled.copiedBuffer("$".getBytes());
//                            p.addLast(new DelimiterBasedFrameDecoder
//                                    (1024,delimiter));
//                            固定长度解码器
//                            p.addLast(new FixedLengthFrameDecoder(1024));

                            p.addLast(new StringDecoder());
                            p.addLast(new TimeClientHandler());
                        }
                    });

            final ChannelFuture f = b.connect("localhost", TimerServer.PORT);
            f.addListener(future ->   System.out.println("连接完成"));
            // 监听客户端关闭，并阻塞等待
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
