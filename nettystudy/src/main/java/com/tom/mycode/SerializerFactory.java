package com.tom.mycode;

import com.tom.mycode.serializer.Serializer;

public class SerializerFactory {

    private static Serializer serializer;

    private SerializerFactory(){

    }
    static {
        serializer = new JdkSerializer();
    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz){
        return serializer.deserialize(bytes,clazz);
    }


    public static byte[] serialize(Object obj){
        return serializer.serialize(obj);
    }


}
