package com.footballclubstatistics.www.service;

import com.footballclubstatistics.www.annotation.ReceiverMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;


@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerStatisticsConsumer {
    private final PlayerStatisticsService playerStatisticsService;

    @ReceiverMessage
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
