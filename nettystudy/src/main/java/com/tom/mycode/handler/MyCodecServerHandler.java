package com.tom.mycode.handler;

import com.tom.mycode.MyEncodeDecodeConstans;
import com.tom.mycode.RpcMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author WangTao
 */
public class MyCodecServerHandler  extends ChannelInboundHandlerAdapter {




    private static ChannelGroup channelGroup = new
            DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //防止内存泄漏 如果是SimpleChannelInboundHandler 会自动释放
        try{
            if (msg instanceof RpcMessage){
                System.out.printf("客户端地址%s 接收到消息:%s \n",ctx.channel()
                        .remoteAddress(),msg);
            }

        }finally {
            ReferenceCountUtil.release(msg);
        }


    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                System.out.println("已经3秒没有接收到客户端的信息了");
                System.out.println("关闭连接");
                ctx.channel().close();
            }else {
                RpcMessage rpcMessage = new RpcMessage();
                rpcMessage.setMessageType(MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_RESPONSE);
                rpcMessage.setData(MyEncodeDecodeConstans.PONG);
                ctx.channel().writeAndFlush(rpcMessage);
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }


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
