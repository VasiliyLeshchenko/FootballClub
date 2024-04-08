package com.footballclubstatistics.www.service;

import com.footballclub.core.dto.ClubDTO;
import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.dto.PlayerStatisticsDTO;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.PlayerStatistics;
import com.footballclub.core.repository.PlayerStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlayerStatisticsServiceTest {
    @InjectMocks
    private PlayerStatisticsService playerStatisticsService;
    @Mock
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Test
    public void savePlayerStatistics() {
        long playerId = 1L;
        long clubId = 1L;
        long statisticsId = 1L;
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(playerId);
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(clubId);
        PlayerStatisticsDTO statisticsDTO =
                new PlayerStatisticsDTO(statisticsId, playerDTO, clubDTO, 1);
        PlayerStatistics savedStatistics =
                new PlayerStatistics(statisticsId, new Player(playerId), new Club(clubId), 3);
        PlayerStatistics expected = new PlayerStatistics(statisticsId, new Player(playerId), new Club(clubId), 4);

        Mockito.when(playerStatisticsRepository.findByPlayerAndClubId(playerId, clubId)).thenReturn(savedStatistics);
        ArgumentCaptor<PlayerStatistics> captor = ArgumentCaptor.forClass(PlayerStatistics.class);

        playerStatisticsService.save(statisticsDTO);

        Mockito.verify(playerStatisticsRepository, Mockito.times(1)).save(captor.capture());
        assertEquals(expected, captor.getValue());
    }

    @Test
    public void savePlayerStatisticsIfSavedStatisticsIsNull() {
        long playerId = 1L;
        long clubId = 1L;
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(playerId);
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(clubId);
        PlayerStatisticsDTO statisticsDTO =
                new PlayerStatisticsDTO(1L, playerDTO, clubDTO, 1);
        PlayerStatistics statistics =
                new PlayerStatistics(1L, new Player(playerId), new Club(clubId), 1);

        Mockito.when(playerStatisticsRepository.findByPlayerAndClubId(playerId, clubId)).thenReturn(null);
        ArgumentCaptor<PlayerStatistics> captor = ArgumentCaptor.forClass(PlayerStatistics.class);

        playerStatisticsService.save(statisticsDTO);

        Mockito.verify(playerStatisticsRepository).save(captor.capture());
        assertEquals(statistics, captor.getValue());
    }

    @Test
    public void increasePlayerStatistics() {
        PlayerStatistics ps1 = new PlayerStatistics();
        PlayerStatistics ps2 = new PlayerStatistics();
        ps1.setGoals(2);
        ps2.setGoals(5);

        int expectedGoals = 2 + 5;

        playerStatisticsService.increment(ps1, ps2);

        assertEquals(expectedGoals, ps1.getGoals());
    }
}
