package com.tom.kafka.serializer;

import com.tom.kafka.entity.Company;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CompanyDeserializer implements Deserializer<Company> {

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
    public Company deserialize(String topic, byte[] data) {
        if (data == null){
            return null;
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
        int nameLength = byteBuffer.getInt();
        byte[] names = new byte[nameLength];
        byteBuffer.get(names);
        String name = null;
        String address = null;
        try {
            name = new String(names,encoding);
            int addressLength = byteBuffer.getInt();
            byte[] addresses = new byte[addressLength];
            byteBuffer.get(addresses);
            address = new String(addresses,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Company(name,address);
    }
}
