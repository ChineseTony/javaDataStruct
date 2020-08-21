package com.tom.protobuf.handler;

import com.google.common.base.Charsets;
import com.tom.protobuf.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tom.protobuf.RedisUtil.ASTERISK_BYTE;
import static com.tom.protobuf.RedisUtil.COLON_BYTE;
import static com.tom.protobuf.RedisUtil.DOLLAR_BYTE;


/**
 * @author WangTao
 */

@ChannelHandler.Sharable
public class RedisHandler extends ChannelInboundHandlerAdapter {

//    * 多维数  $定长数组  \r\n 协议包的结束符
    // 3 set mykey myvalue  +ok\r\n
//    private static final String MSG = "*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$7\r\nmyvalue\r\n";

//    错误消息 -
//    private static final String MSG = "*3\r\n$5\r\nlpus\r\n$5\r\nmylist\r\n$7\r\nmyvalue\r\n";
//      返回长度值  lpush mylist myvalue
//    private static final String MSG = "*3\r\n$5\r\nlpush\r\n$6\r\nmylist\r\n$7\r\nmyvalue\r\n";

//    返回定长字符  get mystring
//    private static final String MSG = "*2\r\n$3\r\nget\r\n$8\r\nmystring\r\n";

    private static final String MSG = "*2\r\n$3\r\nget\r\n$6\r\nmykey1\r\n";



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时：" + new Date());
        ByteBuf msg = Unpooled.buffer(MSG.getBytes().length);
        msg.writeBytes(MSG.getBytes());
        ctx.writeAndFlush(msg);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof ByteBuf){
//                解析 redis协议 字节数组
                ByteBuf in = (ByteBuf)msg;
                byte first = ((ByteBuf) msg).getByte(0);
                //简单字符串
                if (first== RedisUtil.SIMPLE || first == RedisUtil.MINUS_BYTE){
                    System.out.println(RedisUtil.readLine(in));
                }else if (first == COLON_BYTE){
                    System.out.println("返回整数值为"+RedisUtil.getNumber(in));
                }else if (first== DOLLAR_BYTE){
                    System.out.println("定长类型"+RedisUtil.getFixedString(in));
                }else if (first == ASTERISK_BYTE){
                    System.out.println("数组类型");

                }
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时：" + new Date());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
