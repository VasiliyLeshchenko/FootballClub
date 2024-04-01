package com.footballclubapplication.www.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballclubapplication.www.util.CustomProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
public class PlayerStatisticsProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStatistics(PlayerStatisticsDTO statisticsDTO) {
        try {
            //sendMessage(new ObjectMapper().writeValueAsString(statisticsDTO));
            CustomProducer producer = new CustomProducer("localhost:9092");
            producer.send("player.statistics.save", new ObjectMapper().writeValueAsString(statisticsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("player.statistics.save", message);
    }
}
