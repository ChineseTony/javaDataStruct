package com.tom.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerStart {

    private static final String brokenList ="localhost:9092";

    private static final String topic = "test";
    private static final String groupId = "group_id";


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("bootstrap.servers",brokenList);
        properties.setProperty("group.id",groupId);
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(
                    Duration.ofMillis(1000)
            );
            for (ConsumerRecord record : records){
                System.out.println(record.value());
            }

        }

    }
}
