package com.tom.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;



/**
 * @author WangTao
 */
public class MsgpackEncode extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
            throws Exception {

        MessagePack messagePack = new MessagePack();
        byte[] tmpBytes = messagePack.write(msg);
        out.writeBytes(tmpBytes);

    }
}
