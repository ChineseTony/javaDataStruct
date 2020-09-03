package com.tom.mycode.codec;

import com.tom.mycode.MyEncodeDecodeConstans;
import com.tom.mycode.MyHeadMapSerializer;
import com.tom.mycode.ObjectSerializer;
import com.tom.mycode.RpcMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;

import static com.tom.mycode.MyEncodeDecodeConstans.MAGIC_NUMBER;

/**
 * @author WangTao
 *
 * 自定义协议解码器
 *
 *  * <pre>
 *  * 0     1     2     3     4        5     6     7     8     9    10       11     12    13    14   15    16
 *  * +-----+-----+-----+-----+--------+----+----+----+----+----+-------+-----------+-----+-----+-----+-----+
 *  * |   magic   code        |version | Full length            |Head length| messageType|   RequestId       |
 *  * |
 *  * +-----------+-----------+-----------+-----------+-----------+-----------+-----------+-----------------+
 *  * |                                                                                                     |
 *  * |                                   Head Map [Optional]                                               |
 *  * +-----------+-----------+-----------+-----------+-----------+-----------+-----------+-----------------+
 *  * |                                                                                                    |
 *  * |                                         body                                                       |
 *  * |                                                                                                    |
 *  * |                                        ... ...                                                     |
 *  * +----------------------------------------------------------------------------------------------------+
 *
 *  自定义编码器  4b  魔法数  1B version 版本  4 full length 4 head length 2B  messageType 消息类型 1B
 *  requestId 1B  HeadMap map类型 可选  body objct类型数据
 *
 */
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcMessageEncoder.class);


    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage rpcMessage, ByteBuf out)
            throws Exception {
        try {
            int fullLength = MyEncodeDecodeConstans.HEAD_LENGTH;
            int headLength = MyEncodeDecodeConstans.HEAD_LENGTH;

            byte messageType = rpcMessage.getMessageType();
            //写入magic数字
            out.writeBytes(MAGIC_NUMBER);
            out.writeByte(MyEncodeDecodeConstans.VERSION);
            //留出6位置给 full Length 和 headLength full Length(4B) and head length(2B)
            out.writerIndex(out.writerIndex() + 6);

            out.writeByte(messageType);
            out.writeInt(rpcMessage.getRequestId());
            Map<String, String> headMap = rpcMessage.getParams();
            if (headMap != null && !headMap.isEmpty()) {
                int headMapBytesLength = MyHeadMapSerializer.getInstance().encode(headMap, out);
                headLength += headMapBytesLength;
                fullLength += headMapBytesLength;
            }

            byte[] bodyBytes = null;

            //不是心跳
            if (messageType != MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_REQUEST
                    && messageType != MyEncodeDecodeConstans.MSGTYPE_HEARTBEAT_RESPONSE) {
                    //对象序列化
                    bodyBytes = ObjectSerializer.ObjectToByte(rpcMessage.getData());
                    fullLength += bodyBytes.length;
            }
            if (bodyBytes != null){
                out.writeBytes(bodyBytes);
            }
                //暂存写入的位置
            int writeIndex = out.writerIndex();
            out.writerIndex(writeIndex - fullLength + MAGIC_NUMBER.length + 1);
            out.writeInt(fullLength);
            out.writeShort(headLength);
            //重置
            out.writerIndex(writeIndex);

        }catch (Exception e){
            LOGGER.error("Encode request error!", e);
        }

    }
}
