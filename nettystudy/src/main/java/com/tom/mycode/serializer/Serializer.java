package com.tom.mycode.serializer;

public interface Serializer {

    <T> T deserialize(byte[] bytes, Class<T> clazz);


    byte[] serialize(Object obj);
}
