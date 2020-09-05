package com.tom.zhanbao;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;



/**
 * @author WangTao
 */
public class TimerServer {
    public static final int PORT = 8099;


    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(bossGroup, workerGroup)
                    // 设置要被实例化的为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    // 设置 NioServerSocketChannel 的可选项
                    // 设置 NioServerSocketChannel 的处理器
                    .handler(new LoggingHandler(LogLevel.ERROR))
                    .childOption(ChannelOption.SO_SNDBUF, 5)
                    .childOption(ChannelOption.SO_LINGER, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception { // 设置连入服务端的 Client 的 SocketChannel 的处理器
                            ChannelPipeline p = ch.pipeline();
                            //解决粘包问题
                            p.addLast(new LineBasedFrameDecoder(1024));
                            p.addLast(new StringDecoder());
                            p.addLast(new TimerServerHandler());
                        }
                    });

            ChannelFuture channelFuture = b.bind(PORT)
                    .addListener(
                            future ->    System.out.println("服务端启动,启动端口:"+PORT)
                    ).sync();
            channelFuture.channel().closeFuture().sync();


        }catch (Exception e){
            e.printStackTrace();
        } finally{
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
