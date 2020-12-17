package com.tom.kafka.producer;

import com.tom.kafka.entity.Company;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CompanyProducer {
    private static final String brokenList ="localhost:9092";

    private static final String topic = "test";


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","com.tom.kafka.serializer.CompanySerializer");
        properties.setProperty("bootstrap.servers",brokenList);
        KafkaProducer<String, Company> producer = new KafkaProducer<>(properties);
        ProducerRecord<String,Company> record = new ProducerRecord<>(
                topic,new Company("tom","beijing")
        );
        producer.send(record, (metadata, exception) -> {
            if (exception != null){
                exception.printStackTrace();
            }else {
                System.out.println(metadata.partition()+":"+metadata.offset());
            }
        });
        producer.close();
    }
}
