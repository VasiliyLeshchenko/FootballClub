package com.footballclubstatistics.www.service;

import com.footballclub.core.dto.mapper.PlayerStatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PlayerStatisticsConsumer {
    private final PlayerStatisticsService playerStatisticsService;
    Logger logger = LoggerFactory.getLogger(PlayerStatisticsConsumer.class);

/*    @KafkaListener(topics = "player.statistics.save", groupId = "playerConsumer")
    public void listen(String message) {
        logger.debug(message);
        try {
            PlayerStatisticsDTO statisticsDTO = new ObjectMapper().readValue(message, PlayerStatisticsDTO.class);
            playerStatisticsService.save(statisticsDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/

    //Первый способ
    @KafkaListener(topics = "player.statistics.save", groupId = "playerConsumer",
        properties = {
            "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
                "value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
                "spring.json.value.default.type=com.footballclub.core.dto.PlayerStatisticsDTO"})
    public void listen(PlayerStatisticsDTO statisticsDTO) {
        logger.info("Received player statistics message: {}", statisticsDTO);
        playerStatisticsService.save(statisticsDTO);

    }
}
