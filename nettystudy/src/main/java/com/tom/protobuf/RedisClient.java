package com.tom.protobuf;

import com.tom.protobuf.handler.RedisHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @author WangTao
 */
public class RedisClient {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        RedisClient.connect(bootstrap,eventLoopGroup);
    }


    public static void connect(Bootstrap bootstrap,EventLoopGroup eventLoopGroup){
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new LineBasedFrameDecoder(2048));
                            pipeline.addLast(new RedisHandler());
                        }
                    });
            ChannelFuture  future = bootstrap.connect("localhost",6379)
                    .addListener(v -> System.out.println("连接成功")).sync();
            future.channel().closeFuture().sync();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
             eventLoopGroup.shutdownGracefully();
        }
    }
}
