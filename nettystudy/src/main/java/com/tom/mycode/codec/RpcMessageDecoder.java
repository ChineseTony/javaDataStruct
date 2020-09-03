package com.tom.mycode.codec;

import com.tom.mycode.MyEncodeDecodeConstans;
import com.tom.mycode.MyHeadMapSerializer;
import com.tom.mycode.ObjectSerializer;
import com.tom.mycode.RpcMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author WangTao
 */
public class RpcMessageDecoder  extends ByteToMessageDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcMessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
//        读取前4个magic比对一下
        int len = MyEncodeDecodeConstans.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != MyEncodeDecodeConstans.MAGIC_NUMBER[i]){
                throw new IllegalArgumentException("Unknown magic code: " + Arrays.toString(tmp));
            }
        }
        byte version = in.readByte();
        if (version != MyEncodeDecodeConstans.VERSION){
            throw new RuntimeException("version isn't compatible"+version);
        }
        int fullLength = in.readInt();
        short headLength = in.readShort();
        //消息类型
        byte messageType = in.readByte();

        int requestId = in.readInt();

        RpcMessage rpcMessage = new RpcMessage();
        rpcMessage.setRequestId(requestId);
        rpcMessage.setMessageType(messageType);
        int headMapLength = headLength - MyEncodeDecodeConstans.HEAD_LENGTH;
        if (headMapLength > 0) {
            Map<String, String> map = MyHeadMapSerializer.getInstance().decode(in, headMapLength);
            rpcMessage.setParams(map);
        }

        if (messageType == MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_REQUEST) {
            rpcMessage.setData(MyEncodeDecodeConstans.PING);
        }else  if(messageType == MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_RESPONSE){
            rpcMessage.setData(MyEncodeDecodeConstans.PONG);
        } if (messageType != MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_REQUEST
         && messageType != MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_RESPONSE){
            int bodyLength = fullLength - headLength;
            if (bodyLength > 0) {
                byte[] bs = new byte[bodyLength];
                in.readBytes(bs);
                Object tmpValue = ObjectSerializer.ByteToObject(bs);
                rpcMessage.setData(tmpValue);
            }
        }
        out.add(rpcMessage);

    }


}
