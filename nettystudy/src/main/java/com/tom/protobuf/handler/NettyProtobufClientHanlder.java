package com.tom.protobuf.handler;

import com.tom.protobuf.ProtobufClient;
import com.tom.protobuf.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * @author WangTao
 */

@ChannelHandler.Sharable
public class NettyProtobufClientHanlder  extends ChannelInboundHandlerAdapter {

    private static int fcount = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时：" + new Date());
        ctx.fireChannelActive();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof UserInfo.UserMsg) {
                UserInfo.UserMsg userMsg = (UserInfo.UserMsg) msg;
                System.out.println(
                        "客户端接受到的用户信息。编号:" + userMsg.getId() +
                                ",姓名:" + userMsg.getName() + ",年龄:" + userMsg.getAge());

                // 这里返回一个已经接受到数据的状态  把state处理 设置state为1
                UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(1);
                ctx.writeAndFlush(userState);
            } else {
                System.out.println("未知数据!" + msg);
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("循环请求的时间：" + new Date() + "，次数" + fcount);
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            //空闲状态
            if (IdleState.WRITER_IDLE.equals(event.state())){
                //state 为2发送心跳
                UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(2);
                ctx.channel().writeAndFlush(userState);
                fcount++;
            }
        }else {
            super.userEventTriggered(ctx, evt);
        }

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时：" + new Date());
        final EventLoop eventLoop = ctx.channel().eventLoop();
        //重连
        ProtobufClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
