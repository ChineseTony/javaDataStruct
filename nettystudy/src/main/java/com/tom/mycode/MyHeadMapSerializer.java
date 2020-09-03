package com.tom.mycode;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangTao
 */
public class MyHeadMapSerializer {

    private static final MyHeadMapSerializer INSTANCE = new MyHeadMapSerializer();

    private MyHeadMapSerializer() {

    }

    public static MyHeadMapSerializer getInstance() {
        return INSTANCE;
    }

    public int encode(Map<String, String> map, ByteBuf out){
        if (map == null || map.isEmpty() || out == null) {
            return 0;
        }
        int start = out.writerIndex();
        for (Map.Entry<String, String> entry:map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null) {
                writeString(out, key);
                writeString(out, value);
            }
        }
        return out.writerIndex() - start;
    }


    public Map<String, String> decode(ByteBuf in, int length) {
        Map<String, String> map = new HashMap<String, String>();
        if (in == null || in.readableBytes() == 0 || length == 0) {
            return map;
        }
        int tick = in.readerIndex();
        while (in.readerIndex() - tick < length) {
            String key = readString(in);
            String value = readString(in);
            map.put(key, value);
        }

        return map;
    }


    private String readString(ByteBuf in) {
        int length = in.readShort();
        if (length < 0) {
            return null;
        } else if (length == 0) {
            return StringUtils.EMPTY;
        } else {
            byte[] value = new byte[length];
            in.readBytes(value);
            return new String(value, MyEncodeDecodeConstans.DEFAULT_CHARSET);
        }
    }

    private void writeString(ByteBuf out, String key) {
        //空
        if (key == null){
            out.writeShort(-1);
        }else if (key.isEmpty()){
            out.writeShort(0);
        }else {
            byte[] tmp = key.getBytes();
            out.writeShort(tmp.length);
            out.writeBytes(tmp);
        }
    }


}
