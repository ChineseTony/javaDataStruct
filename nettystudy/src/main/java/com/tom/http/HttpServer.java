package com.tom.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpServer {

    private static final int   PORT = 8080;

    public static void main(String[] args) {
        HttpServer.run(HttpServer.PORT);
    }


    public static void run(int port){
        EventLoopGroup work = new NioEventLoopGroup(1);
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(work,boss)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_SNDBUF, 5)
                    .childOption(ChannelOption.SO_LINGER, 100)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception { // 设置连入服务端的 Client 的 SocketChannel 的处理器
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new HttpRequestDecoder());
                            p.addLast(new HttpResponseEncoder());
                            p.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
                            p.addLast(new HttpServerHandler());
                        }
                    });
            ChannelFuture  channelFuture= serverBootstrap.bind(port).sync()
                    .addListener(v ->{
                        System.out.println("启动端口:"+port);
                    });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
