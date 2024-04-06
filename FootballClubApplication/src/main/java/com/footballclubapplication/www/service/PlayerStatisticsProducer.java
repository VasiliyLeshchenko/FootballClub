package com.footballclubapplication.www.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballclubapplication.www.producer.CustomProducer;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
public class PlayerStatisticsProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(PlayerStatisticsProducer.class);

    public void sendStatistics(PlayerStatisticsDTO statisticsDTO) {
        try {
            CustomProducer producer = new CustomProducer("localhost:9092");
            logger.info("Send player statistics to Kafka: {}", statisticsDTO);
            producer.send("player.statistics.save", new ObjectMapper().writeValueAsString(statisticsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("player.statistics.save", message);

    }
}
