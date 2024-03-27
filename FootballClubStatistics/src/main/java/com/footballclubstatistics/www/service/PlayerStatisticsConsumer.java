package com.footballclubstatistics.www.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsConsumer {
    @KafkaListener(topics = "player.statistics.save", groupId = "playerConsumer")
    public void listen(String message) {
        System.out.println("Receive message: " + message);
    }
}
