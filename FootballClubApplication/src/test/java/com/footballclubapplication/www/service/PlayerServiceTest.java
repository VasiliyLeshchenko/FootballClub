package com.footballclubapplication.www.service;

import com.footballclub.core.dto.PlayerStatisticsDTO;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.repository.PlayerRepository;
import com.footballclubapplication.www.producer.CustomProducer;
import kafka.server.MockTierStateMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @InjectMocks
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private ClubService clubService;
    @Mock
    private CustomProducer producer;

    @Test
    void returnAllPlayers() {
        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> expectedResult = List.of(player1, player2);
        Mockito.when(playerRepository.findAll()).thenReturn(expectedResult);

        List<Player> actualResult = playerService.findAll();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void returnOptionalPlayerById() {
        long playerId = 1L;
        Player player = new Player(playerId);
        Optional<Player> expectedResult = Optional.of(player);
        Mockito.when(playerRepository.findById(playerId)).thenReturn(expectedResult);

        Optional<Player> actualResult = playerService.findById(playerId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void save() {
        Player player = new Player();
        player.setName("New Player");

        playerService.save(player);

        Mockito.verify(playerRepository, Mockito.times(1)).save(player);
    }

    @Test
    void update() {
        long playerId = 1L;
        Player player = new Player(playerId);

        playerService.update(player);

        Mockito.verify(playerRepository, Mockito.times(1)).save(player);
    }

    @Test
    void changeClub() {
        long playerId = 1L;
        long oldClubId = 1L;
        long newClubId = 2L;
        Club oldClub = new Club(oldClubId);
        Club newClub = new Club(newClubId);
        Player player = new Player(playerId);
        player.setClub(oldClub);

        Mockito.when(playerService.findById(playerId)).thenReturn(Optional.of(player));
        Mockito.when(clubService.findById(newClubId)).thenReturn(Optional.of(newClub));

        playerService.changeClub(playerId, newClubId);

        Mockito.verify(playerRepository, Mockito.times(1)).save(player);

        assertEquals(newClubId, player.getClub().getId());
    }

    @Test
    void delete() {
        long playerId = 1L;

        playerService.delete(playerId);

        Mockito.verify(playerRepository, Mockito.times(1)).deleteById(playerId);
    }

    //TODO: спросить
    @Test
    void sendStatistics() {
        String topicName = "player.statistics.save";
        PlayerStatisticsDTO statistics = new PlayerStatisticsDTO();

        playerService.sendStatistics(statistics);

        Mockito.verify(producer, Mockito.times(1)).send(topicName, statistics);
    }
}