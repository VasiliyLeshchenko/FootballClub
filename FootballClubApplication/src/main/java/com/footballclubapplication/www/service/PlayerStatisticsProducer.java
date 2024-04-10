package com.footballclubapplication.www.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballclubapplication.www.producer.CustomProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerStatisticsProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStatistics(PlayerStatisticsDTO statisticsDTO) {
        try {
            log.info("Send player statistics to Kafka");
            kafkaTemplate.send("player.statistics.save", new ObjectMapper().writeValueAsString(statisticsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        log.info("Send message to Kafka");
        kafkaTemplate.send("player.statistics.save", message);
    }
}
