package com.tom.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author WangTao
 */
public class MsgpackClientHandler extends ChannelInboundHandlerAdapter {

    private static final int times = 100;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //防止内存泄漏 如果是SimpleChannelInboundHandler 会自动释放
        try{
            System.out.println(msg instanceof User);
            System.out.printf("客户端地址%s 接收到消息:%s \n",ctx.channel()
                        .remoteAddress(),msg);

        }finally {
            ReferenceCountUtil.release(msg);
        }


    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < times; i++) {
            User user = new User();
            user.setName("client"+i);
            user.setPassword("abcd-->"+i);
            ctx.writeAndFlush(user);
        }

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
