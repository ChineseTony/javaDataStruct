package com.tom.echo;

import com.tom.echo.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author WangTao
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            b.group(bossGroup, workerGroup)
                    // 设置要被实例化的为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    // 设置 NioServerSocketChannel 的可选项
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 设置 NioServerSocketChannel 的处理器
//                    .handler(new LoggingHandler(LogLevel.ERROR))
                    .childOption(ChannelOption.SO_SNDBUF, 5)
                    .childOption(ChannelOption.SO_LINGER, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception { // 设置连入服务端的 Client 的 SocketChannel 的处理器
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture channelFuture = b.bind(8090)
                    .addListener(
                            future ->    System.out.println("测试下被触发")
                    ).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            // 优雅关闭两个 EventLoopGroup 对象
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }




}
