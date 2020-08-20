package com.tom.echo;


import com.tom.echo.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;


/**
 * @author WangTao
 */
public class EchoClient {

    public static final String MESSAGE  = "hello\n";

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
                    p.addLast(new LineBasedFrameDecoder(2048));
                    p.addLast(new StringEncoder(StandardCharsets.UTF_8));
                    p.addLast(new StringDecoder(StandardCharsets.UTF_8));
                    p.addLast(new EchoClientHandler());
                }
            });

            final ChannelFuture f = b.connect("localhost",EchoServer.PORT);
            f.addListener(future ->   System.out.println("连接完成"));
            // 监听客户端关闭，并阻塞等待
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
