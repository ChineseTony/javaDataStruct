package com.tom.kafka.serializer;

import com.tom.kafka.entity.Company;
import org.apache.commons.codec.Charsets;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.StringUtils;

import java.nio.ByteBuffer;
import java.util.Map;


/**
 * kafka 自定义 Partitioner  ProducerInterceptor
 */
public class CompanySerializer  implements Serializer<Company> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("serializer.encoding");
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null){
            return null;
        }
        byte[] name = new byte[0],address = new byte[0];
        if (!StringUtils.isEmpty(data.getName())){
            name = data.getName().getBytes(Charsets.toCharset(encoding));
        }
        if (!StringUtils.isEmpty(data.getAddress())){
            address = data.getName().getBytes(Charsets.toCharset(encoding));
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(name.length + address.length + 8);
        byteBuffer.putInt(name.length);
        byteBuffer.put(name);
        byteBuffer.putInt(address.length);
        byteBuffer.put(address);
        return byteBuffer.array();
    }



}
