package com.kafka.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProduceWithKeyDemo {

    private  static final String bootstrapServer="127.0.0.1:9092";
    private  static final String topicName="first_topic";
   private static Logger logger= LoggerFactory.getLogger(ProduceWithKeyDemo.class);
    public static void main(String[] args) throws Exception{
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create produce properties
        for (int i=0;i<10;i++) {


            String message="Hello World "+ Integer.toString(i);
            String key="id_"+ Integer.toString(i);
            logger.info("keys: "+key);
            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName,key, message);

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
            }).get();
            producer.flush();
            producer.close();
        }

        System.out.println("dome");
    }
}
