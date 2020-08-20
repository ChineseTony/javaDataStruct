package com.tom.echo.handler;

import com.google.common.base.Charsets;
import com.tom.echo.EchoClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;


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
        try {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("服务器发送的消息---->"+in.toString(Charsets.UTF_8));
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }




    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
