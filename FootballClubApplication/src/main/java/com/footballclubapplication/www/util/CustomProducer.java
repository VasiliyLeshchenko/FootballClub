package com.footballclubapplication.www.util;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

public class CustomProducer {
    private Properties props;
    private KafkaProducer<String,String> producer;

    public CustomProducer(String url) {
        props = new Properties();
        props.put("bootstrap.servers", url);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    public CustomProducer(Properties props) {
        producer = new KafkaProducer<>(props);
    }

    public void send(String topicName, String message) {
        ProducerRecord<String,String> record = new ProducerRecord<>(topicName, message);
        producer.send(record);
        //producer.close();
    }
}
