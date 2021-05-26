package com.tom.study;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.lang.instrument.Instrumentation;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtao
 * @date 2021/5/19
 */
public class WsServer {

    private static WsServer wsServer;

    private EventLoopGroup mainGroup;

    private Instrumentation instrumentation;

    private EventLoopGroup work;
    private ClassLoader classLoader;
    private ServerBootstrap bootstrap;


    private WsServer(){

    }

    public WsServer(Instrumentation instrumentation, ClassLoader classLoader) {
        mainGroup = new NioEventLoopGroup(1);
        work = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        this.instrumentation = instrumentation;
        this.classLoader = classLoader;
    }

    public synchronized static WsServer getInstance(Instrumentation instrumentation, ClassLoader classLoader){
        if (wsServer == null) {
            wsServer = new WsServer(instrumentation, classLoader);
        }
        return wsServer;
    }


    public  void startServer(){
        ChannelFuture channelFuture= null;
        try {
            bootstrap.group(mainGroup,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new HttpObjectAggregator(1024 * 12));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            pipeline.addLast(new WsServerHandler(instrumentation,classLoader));
                        }
                    });
            channelFuture = bootstrap.bind(8888).sync()
                    .addListener(v ->
                            System.out.println("启动端口:"+8888)
                    );
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopServer(){
        if (work != null) {
            work.shutdownGracefully();
        }
        if (mainGroup != null) {
            mainGroup.shutdownGracefully();
        }
        if (WsServer.wsServer != null){
            WsServer.wsServer = null;
        }
    }
}
