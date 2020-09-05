package com.tom.msgpack;

import io.lettuce.core.StrAlgoArgs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author WangTao
 */
public class MsgpackServerHandler  extends ChannelInboundHandlerAdapter {

    private static final int times = 100;



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //防止内存泄漏 如果是SimpleChannelInboundHandler 会自动释放
        try{
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
            user.setName("server"+i);
            user.setPassword("12345");
            ctx.write(user);
        }
        ctx.flush();

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
