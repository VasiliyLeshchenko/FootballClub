package com.footballclubstatistics.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerStatisticsConsumer {
    private final PlayerStatisticsService playerStatisticsService;

    //Первый способ
    @KafkaListener(topics = "player.statistics.save", groupId = "playerConsumer",
        properties = {
            "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
                "value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
                "spring.json.value.default.type=com.footballclub.core.dto.PlayerStatisticsDTO"})
    public void listen(PlayerStatisticsDTO statisticsDTO) {
        log.info("Received player statistics message");
        playerStatisticsService.save(statisticsDTO);

    }
}
