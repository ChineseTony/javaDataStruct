package com.tom.zhanbao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * @author WangTao
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {



    public static final String MESSAGE  =DateFormatUtil.format(new Date())+
            System.getProperty("line.separator");

    public static int count = 0;


    public static final int TIMES = 100;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //防止内存泄漏 如果是SimpleChannelInboundHandler 会自动释放
        try{
//            if (msg instanceof ByteBuf){
//                ByteBuf buf = ((ByteBuf) msg);
//                byte[] tmp = new byte[buf.readableBytes()];
//                buf.readBytes(tmp);
//                System.out.printf("服务端发送的消息地接收到消息:%s \n",
//                        new String(tmp,"utf-8") + "count:-->" + count++);
//            }
            if (msg instanceof String){
                System.out.printf("客户端地址%s 接收到消息:%s \n",ctx.channel()
                        .remoteAddress(),msg + ",count:-->" + count++);
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送100次 模拟粘包问题
        for (int i = 0; i < TIMES; i++) {
            ByteBuf  firstMessage= Unpooled.buffer(MESSAGE.getBytes().length);
//            System.out.println(MESSAGE);
            firstMessage.writeBytes(MESSAGE.getBytes());
            ctx.writeAndFlush(firstMessage);
        }

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
