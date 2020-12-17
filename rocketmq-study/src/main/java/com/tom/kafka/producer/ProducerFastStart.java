package com.tom.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerFastStart {

    private static final String brokenList ="localhost:9092";

    private static final String topic = "test";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("bootstrap.servers",brokenList);
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String,String> record = new ProducerRecord<>(
                topic,"hello,kafka"
        );
        producer.send(record);
        producer.close();
    }
}
