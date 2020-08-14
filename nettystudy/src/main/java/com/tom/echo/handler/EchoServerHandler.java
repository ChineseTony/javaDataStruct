package com.tom.echo.handler;


import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author WangTao
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        System.out.println("接收到消息：" + new String(bytes,"utf-8"));

        ByteBuf in = (ByteBuf) msg;
        System.out.printf("客户端地址%s 接收到消息:%s ",ctx.channel().remoteAddress(),in.toString(Charsets.UTF_8));
        ctx.writeAndFlush("hello I am server");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("hello I am server");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
