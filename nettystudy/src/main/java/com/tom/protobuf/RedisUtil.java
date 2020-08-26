package com.tom.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author WangTao
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public static final String CHARSET = "UTF-8";

    private RedisUtil(){

    }

//    简单字符串 +
    public static final byte SIMPLE = '+';
    //定长类型
    public static final byte DOLLAR_BYTE = '$';
    //数组类型
    public static final byte ASTERISK_BYTE = '*';
    //错误类型
    public static final byte MINUS_BYTE = '-';
    //数值类型
    public static final byte COLON_BYTE = ':';
    public static final byte DIRE = '\r';
    public static final byte TMP= '\n';


    private static int findLineEndIndex(ByteBuf buffer) {
        int index = buffer.forEachByte(ByteProcessor.FIND_LF);
        return (index > 0 && buffer.getByte(index - 1) == '\r') ? index : -1;
    }


    //简单字符串 +ok\r\n 读取字节数组进行解析
    public static String readLine(ByteBuf buffer) {
        //找到最后一个\r
        int lineEndIndex = findLineEndIndex(buffer);
        if (lineEndIndex > -1) {
            int lineStartIndex = buffer.readerIndex();
            // 计算字节长度
            int size = lineEndIndex - lineStartIndex - 1;
            byte[] bytes = new byte[size];
            buffer.readerIndex(lineStartIndex+1);
            buffer.readBytes(bytes);
            // 重置读游标为\r\n之后的第一个字节
            buffer.readerIndex(lineEndIndex + 1);
            buffer.markReaderIndex();
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static String getNumber(ByteBuf buffer) {
        //找到最后一个\r
        int lineEndIndex = findLineEndIndex(buffer);
        if (lineEndIndex > -1) {
            int lineStartIndex = buffer.readerIndex();
            // 计算字节长度
            int size = lineEndIndex - lineStartIndex - 1;
            byte[] bytes = new byte[size];

            buffer.readBytes(bytes);
            // 重置读游标为\r\n之后的第一个字节
            buffer.readerIndex(lineEndIndex + 1);
            buffer.markReaderIndex();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size-1; i++) {
                sb.append((char)bytes[i+1] - '0');
            }

            return sb.toString();
        }
        return null;
    }

    public static String getFixedString(ByteBuf buffer) {
        //找到最后一个\r   $4\r\nhell\r\n
        int lineEndIndex = findLineEndIndex(buffer);
        if (lineEndIndex > -1) {
            //第一步找出len
            Long length = getLen(buffer);
            if (length == null || length < 0){
                return null;
            }
            //返回空字符串
            if (length == 0){
                return "";
            }
            //读取字符串
            int readLength = (int) length.longValue();
            if (buffer.readableBytes() > readLength) {
                byte[] bytes = new byte[readLength];
                buffer.readBytes(bytes);
                // 重置读游标为\r\n之后的第一个字节
                return new String(bytes, StandardCharsets.UTF_8);
            }

        }
        return null;
    }

    private static Long getLen(ByteBuf buf){
       int index = findLineEndIndex(buf);
       if ((index > 0 && buf.getByte(index - 1) == DIRE)){
           //求出长度
           Long result = 0L;
           int lineStartIndex = buf.readerIndex();
           int size = index - lineStartIndex - 1;
           byte[] bytes = new byte[size];

           buf.readerIndex(lineStartIndex);
           buf.readBytes(bytes);
           buf.readerIndex(index + 1);
           //重置读取长度
           buf.markReaderIndex();

           for (int i = 0; i < size-1; i++) {
               result = result * 10 + (char)bytes[i+1] - '0' ;
           }
           logger.info("定长长度是{}",result);
           return result;
       }else {
           return null;
       }
    }



}
