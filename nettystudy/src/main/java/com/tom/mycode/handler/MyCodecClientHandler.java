package com.tom.mycode.handler;


import com.tom.mycode.MyEncodeDecodeConstans;
import com.tom.mycode.RpcMessage;

import com.tom.protobuf.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangTao
 */
public class MyCodecClientHandler extends ChannelInboundHandlerAdapter {

    private RpcMessage rpcMessage;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final int COUNT = 3;

    public MyCodecClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        for (int i = 0; i < 100; i++) {
            rpcMessage = new RpcMessage();
            rpcMessage.setRequestId(i);
            rpcMessage.setMessageType(MyEncodeDecodeConstans.MSGTYPE_RESQUEST);
            rpcMessage.setData("study netty"+i);
            Map<String, String> map = new HashMap<>();
            map.put("age",""+i);
            map.put("name","tom"+i);
            rpcMessage.setParams(map);
            ctx.writeAndFlush(rpcMessage);
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof RpcMessage){
                System.out.println("服务器发送的消息---->"+msg);
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            //空闲状态
            if (IdleState.WRITER_IDLE.equals(event.state())){
                int tmp = atomicInteger.incrementAndGet();
                if (tmp <=  COUNT){
                    RpcMessage rpcMessage = new RpcMessage();
                    rpcMessage.setRequestId(tmp);
                    rpcMessage.setMessageType(MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_REQUEST);
                    ctx.channel().writeAndFlush(rpcMessage);
                }else {
                    System.out.println("超过最大连接次:"+COUNT+",断开连接");
                    ctx.channel().close();
                }

            }
        }else {
            super.userEventTriggered(ctx, evt);
        }

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
