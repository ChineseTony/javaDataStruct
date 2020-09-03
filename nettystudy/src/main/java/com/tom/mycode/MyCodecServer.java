package com.tom.mycode;


import com.tom.mycode.codec.RpcMessageDecoder;
import com.tom.mycode.codec.RpcMessageEncoder;
import com.tom.mycode.handler.MyCodecServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


/**
 * @author WangTao
 */
public class MyCodecServer {


    public static final int PORT = 8099;

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
                    .handler(new LoggingHandler(LogLevel.ERROR))
                    .childOption(ChannelOption.SO_SNDBUF, 5)
                    .childOption(ChannelOption.SO_LINGER, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception { // 设置连入服务端的 Client 的 SocketChannel 的处理器
                            ChannelPipeline p = ch.pipeline();
//                           心跳检测机制
                            p.addLast(new IdleStateHandler(3,
                                    2
                                    , 0, TimeUnit.SECONDS));
                            p.addLast(new RpcMessageEncoder());
                            p.addLast(new RpcMessageDecoder());
                            p.addLast(new MyCodecServerHandler());
                        }
                    });

            ChannelFuture channelFuture = b.bind(PORT)
                    .addListener(
                            future ->    System.out.println("服务端启动,启动端口:"+PORT)
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
