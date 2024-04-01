package com.footballclubapplication.www.service;

import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.dto.mapper.GoalMapper;
import com.footballclub.core.entity.Game;
import com.footballclub.core.exception.GameNotFoundException;
import com.footballclubapplication.www.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.footballclub.core.dto.PlayerStatisticsDTO;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public Optional<Game> findById(long id) {
        return gameRepository.findById(id);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }
    public List<Game> getHomeGamesByClubId(long clubId) {
        return gameRepository.getHomeGamesByClubId(clubId);
    }

    public List<Game> getAwayGamesByClubId(long clubId) {
        return gameRepository.getAwayGamesByClubId(clubId);
    }

    //TODO: новое
    public void goal(GoalDTO goal) {
        Game game = GoalMapper.toGame(goal);
        PlayerStatisticsDTO playerStatistics = GoalMapper.toPlayerStatisticsDTO(goal);

        incrementScore(game);
        playerService.sendStatistics(playerStatistics);
    }

    public void incrementScore(Game gameNewScore) {
        Game game = findById(gameNewScore.getId()).orElseThrow(() -> new GameNotFoundException("Game not found"));

        int homeClubScore = game.getHomeClubScore() + gameNewScore.getHomeClubScore();
        int awayClubScore = game.getAwayClubScore() + gameNewScore.getAwayClubScore();

        game.setHomeClubScore(homeClubScore);
        game.setAwayClubScore(awayClubScore);

        update(game);
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    @Transactional
    public void update(Game game) {
        gameRepository.save(game);
    }

    public List<Game> getWinGamesByClubId(long clubId) {
        return gameRepository.getWinGamesBy(clubId);
    }

    public List<Game> getLoseGamesByClubId(long clubId) {
        return gameRepository.getLoseGamesBy(clubId);
    }

    public void delete(long id) {
        gameRepository.deleteById(id);
    }
}
