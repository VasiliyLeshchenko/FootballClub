package com.footballclubstatistics.www.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
public class PlayerStatisticsConsumer {
    private final PlayerStatisticsService playerStatisticsService;

    @KafkaListener(topics = "player.statistics.save", groupId = "playerConsumer")
    public void listen(String message) {
        System.out.println(message);
        try {
            PlayerStatisticsDTO statisticsDTO = new ObjectMapper().readValue(message, PlayerStatisticsDTO.class);
            playerStatisticsService.save(statisticsDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
