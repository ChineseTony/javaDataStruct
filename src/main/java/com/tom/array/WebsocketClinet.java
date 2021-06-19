package com.tom.array;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author wangtao
 * @date 2021/6/5
 */
public class WebsocketClinet {
    private static final String SERVER_URI = "ws://localhost:8888/ws";

    public static void main(String[] args) throws URISyntaxException {

        WebSocketClientHandshaker newHandshaker = WebSocketClientHandshakerFactory.newHandshaker(
                new URI(SERVER_URI),
                WebSocketVersion.V13, null, true, new DefaultHttpHeaders());
        final WebSocketClientProtocolHandler websocketClientHandler = new
                WebSocketClientProtocolHandler(newHandshaker);

        MyHandler handler = new MyHandler(newHandshaker);

        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpClientCodec());
                        pipeline.addLast(new HttpObjectAggregator(1024 * 8));
                        pipeline.addLast(handler);
                    }
                });

        ChannelFuture future =   bootstrap.connect("localhost",8888);

        future.addListener((ChannelFutureListener)
                future1 -> System.out.println("开始连接"));
        try {
            Channel channel =future.sync().channel();
            handler.handshakeFuture().sync();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class MyHandler extends SimpleChannelInboundHandler<Object>{

        private final WebSocketClientHandshaker webSocketClientHandshaker;


        private ChannelPromise handshakeFuture;

        public MyHandler(WebSocketClientHandshaker handshaker){
            this.webSocketClientHandshaker = handshaker;
        }

        @Override
        public void channelActive(ChannelHandlerContext channelHandlerContext) {
            Channel channel = channelHandlerContext.channel();
            webSocketClientHandshaker.handshake(channel);
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            handshakeFuture = ctx.newPromise();
        }



        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object tmp) throws Exception {
            Channel ch = ctx.channel();
            if (!webSocketClientHandshaker.isHandshakeComplete()) {
                webSocketClientHandshaker.finishHandshake(ch, (FullHttpResponse) tmp);
                System.out.println("websocket connect");
                handshakeFuture.setSuccess();
                System.out.println("done");
                return;
            }
            System.out.println("hello");
            if (tmp instanceof TextWebSocketFrame) {
                TextWebSocketFrame frame = (TextWebSocketFrame) tmp;
                System.out.println("---->" + frame.text());
            }
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msg = console.readLine();
                if (msg == null) {
                    break;
                } else if ("bye".equals(msg.toLowerCase())) {
                    ch.writeAndFlush(new CloseWebSocketFrame());
                    ch.closeFuture().sync();
                    break;
                } else if ("ping".equals(msg.toLowerCase())) {
                    WebSocketFrame frame = new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[]{8, 1, 8, 1}));
                    ch.writeAndFlush(frame);
                } else {
                    WebSocketFrame frame = new TextWebSocketFrame(msg);
                    ch.writeAndFlush(frame);
                }
            }
        }

        public ChannelFuture handshakeFuture() {
            return handshakeFuture;
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            if (!handshakeFuture.isDone()) {
                handshakeFuture.setFailure(cause);
            }

            ctx.close();

        }
    }

}
