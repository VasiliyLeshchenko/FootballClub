package com.footballclubapplication.www.service;

import com.footballclub.core.dto.*;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Game;
import com.footballclub.core.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @InjectMocks
    private GameService gameService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlayerService playerService;

    @Test
    void findById() {
        long gameId = 1L;
        Game game = new Game(gameId);
        Optional<Game> expectedResult = Optional.of(game);
        Mockito.when(gameRepository.findById(gameId)).thenReturn(expectedResult);

        Optional<Game> actualResult = gameService.findById(gameId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void returnAllGames() {
        Game game1 = new Game();
        Game game2 = new Game();
        List<Game> extendsGames = List.of(game1, game2);
        Mockito.when(gameRepository.findAll()).thenReturn(extendsGames);

        List<Game> actualGames = gameService.findAll();

        assertEquals(extendsGames, actualGames);
    }

    @Test
    void returnHomeGamesByClubId() {
        long clubId = 3L;
        Club club = new Club(clubId);
        Game game1 = new Game();
        Game game2 = new Game();
        game1.setHomeClub(club);
        game2.setHomeClub(club);
        List<Game> expectedGames = List.of(game1, game2);
        Mockito.when(gameRepository.getHomeGamesByClubId(clubId)).thenReturn(expectedGames);

        List<Game> actualGames = gameService.getHomeGamesByClubId(clubId);

        assertEquals(expectedGames, actualGames);
    }

    @Test
    void returnAwayGamesByClubId() {
        long clubId = 3L;
        Club club = new Club(clubId);
        Game game1 = new Game();
        Game game2 = new Game();
        game1.setAwayClub(club);
        game2.setAwayClub(club);
        List<Game> expectedGames = List.of(game1, game2);
        Mockito.when(gameRepository.getAwayGamesByClubId(clubId)).thenReturn(expectedGames);

        List<Game> actualGames = gameService.getAwayGamesByClubId(clubId);

        assertEquals(expectedGames, actualGames);
    }

    @Test
    void registerGoal() {
        long gameId = 1L;
        PlayerDTO playerDTO = new PlayerDTO();
        ClubDTO homeClub = new ClubDTO();
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(gameId);
        GoalDTO goalDTO = new GoalDTO(gameDTO, playerDTO, homeClub, true, 3);
        Game game = new Game(gameId);
        game.setHomeClubScore(0);
        game.setAwayClubScore(1);
        Mockito.when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        gameService.goal(goalDTO);

        Mockito.verify(gameRepository, Mockito.times(1)).findById(gameId);
        Mockito.verify(gameRepository, Mockito.times(1)).save(game);
        Mockito.verify(playerService, Mockito.times(1)).sendStatistics(Mockito.any());
    }

    @Test
    void incrementScore() {
        Game incrementedGame = new Game(2L);
        incrementedGame.setHomeClubScore(1);
        incrementedGame.setAwayClubScore(3);
        Game gameRecord = new Game(2L);
        gameRecord.setHomeClubScore(2);
        gameRecord.setAwayClubScore(1);
        Mockito.when(gameRepository.findById(incrementedGame.getId())).thenReturn(Optional.of(gameRecord));

        Game actualGame = gameService.incrementScore(incrementedGame);

        assertEquals(gameRecord, actualGame);
    }

    @Test
    void getWinGamesByClubId() {
        long clubId = 3L;
        Club ourClub = new Club(clubId);
        Club awayClub = new Club();
        Game game1 = new Game(3L, ourClub, awayClub, 4, 3, Date.valueOf("2000-01-01"));
        Game game2 = new Game(4L, awayClub, ourClub, 2, 3, Date.valueOf("2000-01-02"));
        List<Game> expectedGames = List.of(game1, game2);
        Mockito.when(gameRepository.getWinGamesByClubId(clubId)).thenReturn(expectedGames);

        List<Game> actualGames = gameService.getWinGamesByClubId(clubId);

        assertEquals(expectedGames, actualGames);
    }

    @Test
    void getLoseGamesByClubId() {
        long clubId = 3L;
        Club ourClub = new Club(clubId);
        Club awayClub = new Club();
        Game game1 = new Game(3L, ourClub, awayClub, 2, 3, Date.valueOf("2000-01-01"));
        Game game2 = new Game(4L, awayClub, ourClub, 5, 3, Date.valueOf("2000-01-02"));
        List<Game> expectedGames = List.of(game1, game2);
        Mockito.when(gameRepository.getLoseGamesByClubId(clubId)).thenReturn(expectedGames);

        List<Game> actualGames = gameService.getLoseGamesByClubId(clubId);

        assertEquals(expectedGames, actualGames);
    }

    @Test
    void saveGameInDatabase() {
        Club homeClub = new Club();
        Club awayClub = new Club();
        Game game = new Game(3L, homeClub, awayClub, 5, 3, Date.valueOf("2000-01-01"));

        gameService.save(game);

        Mockito.verify(gameRepository, Mockito.times(1)).save(game);
    }

    @Test
    void updateGameInDatabase() {
        Club homeClub = new Club();
        Club awayClub = new Club();
        Game game = new Game(3L, homeClub, awayClub, 5, 3, Date.valueOf("2000-01-01"));

        gameService.update(game);

        Mockito.verify(gameRepository, Mockito.times(1)).save(game);
    }

    @Test
    void deleteGameById() {
        long gameId = 3L;

        gameService.delete(gameId);

        Mockito.verify(gameRepository, Mockito.times(1)).deleteById(gameId);
    }
}