package com.footballclubstatistics.www.service;

import com.footballclub.core.dto.mapper.PlayerStatisticsMapper;
import com.footballclub.core.entity.PlayerStatistics;
import com.footballclub.core.repository.PlayerStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

/**
 * Service class for the player statistics class with property: <b>playerStatisticsRepository</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerStatisticsService {
    /** Player statistics repository property */
    private final PlayerStatisticsRepository playerStatisticsRepository;
    
    /**
     * The method saves a data from statisticsDTO to the database
     * @param statisticsDTO player statistics DTO
     */
    public void save(PlayerStatisticsDTO statisticsDTO) {
        log.info("Save player statistics");
        PlayerStatistics receiveStatistics
                = PlayerStatisticsMapper.INSTANCE.toPlayerStatistics(statisticsDTO);
        PlayerStatistics savedStatistics
                = playerStatisticsRepository.findByPlayerAndClubId(
                        statisticsDTO.getPlayer().getId(),
                        statisticsDTO.getClub().getId()
                );

        if (savedStatistics != null) {
            increment(savedStatistics, receiveStatistics);
        } else {
            savedStatistics = receiveStatistics;
        }

        playerStatisticsRepository.save(savedStatistics);
    }

    /**
     * The method increments the first player statistics on the second player statistics
     * @param s1 first player statistics
     * @param s2 second player statistics
     */
    public void increment(PlayerStatistics s1, PlayerStatistics s2) {
        log.info("Increment player statistics");
        s1.setGoals(s1.getGoals() + s2.getGoals());
    }
}
