package com.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProduceDemo {

    private  static final String bootstrapServer="127.0.0.1:9092";
    private  static final String topicName="first_topic";

    public static void main(String[] args) {
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create produce properties
        KafkaProducer<String,String> producer=new KafkaProducer<String, String>(properties);
        ProducerRecord<String,String> record=new ProducerRecord<String, String>( "first_topic","Hello Kafaka from java");

        //create produce

        //send data
        producer.send(record);
        producer.flush();
        producer.close();
        System.out.println("dome");
    }
}
