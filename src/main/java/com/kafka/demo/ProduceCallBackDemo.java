package com.kafka.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProduceCallBackDemo {

    private  static final String bootstrapServer="127.0.0.1:9092";
    private  static final String topicName="first_topic";
   private static Logger logger= LoggerFactory.getLogger(ProduceCallBackDemo.class);
    public static void main(String[] args) {
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create produce properties
        for (int i=0;i<10;i++) {
            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "Hello Kafaka from java " +i);

            //create produce

            //send data
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                    if (e == null) {
                        logger.info("message recived \n" + "Topic: " +
                                recordMetadata.topic() + "\n Partitions: " +
                                recordMetadata.partition() + "\n Offset : " +
                                recordMetadata.offset() + "\n Time stamp" +
                                recordMetadata.timestamp());
                    } else {
                        logger.error("error while producing message" + e);
                    }
                }
            });
            producer.flush();
            producer.close();
        }

        System.out.println("dome");
    }
}
