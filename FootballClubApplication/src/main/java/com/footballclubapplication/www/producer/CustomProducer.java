package com.footballclubapplication.www.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class CustomProducer {
    private KafkaProducer<String,String> producer;

    /**
     * The constructor receives a server's url,
     * creates a {@linkplain Properties} instance,
     * sets to the key.serializer {@linkplain org.apache.kafka.common.serialization.StringSerializer},
     * sets tho the value.serializer {@linkplain org.apache.kafka.common.serialization.StringSerializer}
     * @param url the server's url
     */
    public CustomProducer(String url) {
        log.info("Create CustomProducer with url {}", url);
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
        log.info("Sending message to topic {} with message {}", topicName, message);
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
        log.info("Sending message to topic {} with key {} and message {}",topicName, key, message);
        producer.send(record);
    }

    /**
     * The method sends message to Kafka
     * @param topicName topic name
     * @param value value
     */
    public <V> void send(String topicName, V value ) {
        try {
            ProducerRecord<String,String> record =
                new ProducerRecord<>(topicName, new ObjectMapper().writeValueAsString(value));

            log.info("Sending message to topic {} with value {}", topicName, value);
            producer.send(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
