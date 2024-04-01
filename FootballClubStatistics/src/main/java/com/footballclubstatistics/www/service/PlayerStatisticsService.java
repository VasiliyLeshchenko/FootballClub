package com.footballclubstatistics.www.service;

import com.footballclub.core.dto.mapper.PlayerStatisticsMapper;
import com.footballclub.core.entity.PlayerStatistics;
import com.footballclubstatistics.www.repository.PlayerStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.footballclub.core.dto.PlayerStatisticsDTO;

@RequiredArgsConstructor
@Service
public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;

    public void save(PlayerStatisticsDTO statisticsDTO) {
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

    public void increment(PlayerStatistics s1, PlayerStatistics s2) {
        s1.setGoals(s1.getGoals() + s2.getGoals());
    }
}
