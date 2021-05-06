package com.tom.classparse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tom
 */
public class ParseClassFile {

    private static final int BSIZE = 1024 * 5;

    private static final String MAGIC = "cafebabe";

    public static void main(String[] args) {
//        ByteBuffer buffer =
//                new File

        File file = new File("/Users/tom/ideaprojects/javaDataStruct/target/classes/com/tom/array/ABCChian.class");
        try {
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            ByteBuffer buff = ByteBuffer.allocateDirect(BSIZE);
            fileChannel.read(buff);
            //读之前需要调用flip
            buff.flip();
            byte[] bytes = new byte[4];
            buff.get(bytes);
            if (!MAGIC.equalsIgnoreCase(HexUtil.byteArrToHex(bytes))){
                throw new RuntimeException("not class file");
            }
//             主版本号  jdk1.8
            bytes = new byte[2];
            buff.get(bytes);
            int maior = HexUtil.toInt(bytes);
            System.out.println(maior);
//            副版本号
            bytes = new byte[2];
            buff.get(bytes);
            int minor =HexUtil.toInt(bytes);
            System.out.println(minor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
