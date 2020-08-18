package com.tom.echo.handler;


import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author WangTao
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public static final String MESSAGE  = "hello I am server";

    private  ByteBuf firstMessage;

    private static ChannelGroup channelGroup = new
            DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.printf("客户端地址%s 接收到消息:%s \n",ctx.channel().remoteAddress(),in.toString(Charsets.UTF_8));

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        System.out.printf("上线1人，一共有%d人\n",channelGroup.size());
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        System.out.printf("下线1人，当前还有%d人\n",channelGroup.size());
        channelGroup.forEach(v -> {
            String writeMsg = String.format("下线1人，当前还有%d人\n",channelGroup.size());
            ByteBuf tmp = Unpooled.buffer(writeMsg.getBytes().length);
            tmp.writeBytes(writeMsg.getBytes());
            v.writeAndFlush(tmp);
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        firstMessage = Unpooled.buffer(MESSAGE.getBytes().length);
        firstMessage.writeBytes(MESSAGE.getBytes());
        ctx.writeAndFlush(firstMessage);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        下线
        System.out.printf("%s下线了",ctx.channel().remoteAddress());
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
