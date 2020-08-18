package com.tom.echo.handler;

import com.google.common.base.Charsets;
import com.tom.echo.EchoClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * @author WangTao
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;


    public EchoClientHandler() {
        firstMessage = Unpooled.buffer(EchoClient.MESSAGE.getBytes().length);
        firstMessage.writeBytes(EchoClient.MESSAGE.getBytes());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("服务器发送的消息---->"+in.toString(Charsets.UTF_8));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
//        System.out.println("关闭channel");
//        ctx.close();
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
