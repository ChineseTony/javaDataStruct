package com.tom.protobuf.handler;

import com.tom.protobuf.UserInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;


/**
 * @author WangTao
 */

@ChannelHandler.Sharable
public class NettyProtobufServerHanlder extends ChannelInboundHandlerAdapter {

    private static int idle_count = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("连接客户端%s\n",ctx.channel().remoteAddress());
        UserInfo.UserMsg userMsg = UserInfo.UserMsg.newBuilder().setId(1)
                .setName("tom").setAge(22).build();
        ctx.writeAndFlush(userMsg);

    }


    /**
     * 心跳检测
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                System.out.println("已经5秒没有接收到客户端的信息了");
                if (idle_count > 1) {
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
                idle_count++;
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof UserInfo.UserMsg) {
                UserInfo.UserMsg userState = (UserInfo.UserMsg) msg;
                if (userState.getState() == 1) {
                    System.out.println("客户端业务处理成功!");
                } else if(userState.getState() == 2){
                    System.out.println("接受到客户端发送的心跳!");
                }else{
                    System.out.println("未知命令!");
                }
            } else {
                System.out.println("未知数据--->!" + msg);
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
