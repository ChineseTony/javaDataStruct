package com.tom.msgpack;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;



/**
 * @author WangTao
 */
public class MsgpackServer {

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

                            //   长度 + 数据内容  解决粘包问题
                            // @link https://blog.csdn.net/u010853261/article/details/55803933
                            p.addLast(new LengthFieldBasedFrameDecoder(65535,
                                    0,2,0,2));
                            p.addLast(new LengthFieldPrepender(2));
                            p.addLast(new MsgpackEncode());
                            p.addLast(new MsgpackDecoder());
                            p.addLast(new MsgpackServerHandler());
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
