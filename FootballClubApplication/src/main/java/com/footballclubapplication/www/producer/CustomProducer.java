package com.footballclubapplication.www.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class CustomProducer {
    private KafkaProducer<String,String> producer;
    private Logger logger = LoggerFactory.getLogger(CustomProducer.class);

    /**
     * The constructor receives a server's url,
     * creates a {@linkplain Properties} instance,
     * sets to the key.serializer {@linkplain org.apache.kafka.common.serialization.StringSerializer},
     * sets tho the value.serializer {@linkplain org.apache.kafka.common.serialization.StringSerializer}
     * @param url the server's url
     */
    public CustomProducer(String url) {
        Properties props = new Properties();
        props.put("bootstrap.servers", url);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    /**
     * The constructor receives {@linkplain Properties}
     * @param props {@linkplain KafkaProducer} properties
     */
    public CustomProducer(Properties props) {
        producer = new KafkaProducer<>(props);
    }

    /**
     * The method sends message to Kafka
     * @param topicName topic name
     * @param message message
     */
    public void send(String topicName, String message) {
        ProducerRecord<String,String> record = new ProducerRecord<>(topicName, message);
        logger.info("Sending message to topic {} with message {}", topicName, message);
        producer.send(record);
    }

    /**
     * The method sends message to Kafka
     * @param topicName topic name
     * @param key key
     * @param message message
     */
    public void send(String topicName, String key, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, message);
        logger.info("Sending message to topic {} with key {} and message {}",topicName, key, message);
        producer.send(record);
    }
}
