package com.tom.study;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author wangtao
 * @date 2021/5/19
 */
public class WsServerHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private ConcurrentMap<String,Channel> map = new ConcurrentHashMap<>(16);

    private Instrumentation instrumentation;



    public WsServerHandler(Instrumentation instrumentation){
        this.instrumentation = instrumentation;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        String data = msg.text();
        System.out.println("----->"+data);
        ctx.writeAndFlush(new TextWebSocketFrame("hello client"));
        if ("all".equalsIgnoreCase(data)) {
            Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
            StringBuilder stringBuilder = new StringBuilder();
            for (Class clazz :allLoadedClasses) {
                stringBuilder.append("类型").append(clazz.getSimpleName());
            }
            ctx.writeAndFlush(new TextWebSocketFrame(
                stringBuilder.toString()
            ));
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        map.put(ctx.name(),ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端断开链接"+channel.id().asLongText());
        map.remove(ctx.name());
    }
}
