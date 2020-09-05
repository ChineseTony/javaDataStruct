package com.tom.msgpack;

import com.tom.echo.EchoServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


/**
 * @author WangTao
 */
public class MsgpackClient {




    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建 Bootstrap 对象
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //   长度 + 数据内容  解决粘包问题
                            p.addLast(new LengthFieldBasedFrameDecoder(65535,
                                    0,2,0,2));
                            p.addLast(new LengthFieldPrepender(2));
                            p.addLast(new MsgpackEncode());
                            p.addLast(new MsgpackDecoder());
                            p.addLast(new MsgpackClientHandler());
                        }
                    });

            final ChannelFuture f = b.connect("localhost", EchoServer.PORT);
            f.addListener(future ->   System.out.println("连接完成"));
            // 监听客户端关闭，并阻塞等待
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
