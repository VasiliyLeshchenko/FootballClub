package com.footballclubapplication.www.service;

import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.dto.mapper.GoalMapper;
import com.footballclub.core.entity.Game;
import com.footballclub.core.exception.GameNotFoundException;
import com.footballclub.core.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.footballclub.core.dto.PlayerStatisticsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the country class with properties: <b>gameRepository</b>, <b>playerService</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {
    /**
     * Game repository property
     * @see GameRepository
     */
    private final GameRepository gameRepository;
    /** Player service property */
    private final PlayerService playerService;

    /**
     * The method gets an optional game value by game id
     * @param id game id
     * @return an optional game value
     */
    public Optional<Game> findById(long id) {
        log.info("Finding game by id: {}", id);
        return gameRepository.findById(id);
    }

    /**
     * The method gets a list of all games from the database
     * @return a list of all games
     */
    public List<Game> findAll() {
        log.info("Finding all games");
        return gameRepository.findAll();
    }

    /**
     * The method gets a list of home games by club id
     * @param clubId club id
     * @return a list of games
     */
    public List<Game> getHomeGamesByClubId(long clubId) {
        log.info("Finding home games for club with id: {}", clubId);
        return gameRepository.getHomeGamesByClubId(clubId);
    }

    /**
     * The method gets a list of away games by club id
     * @param clubId club id
     * @return a list of games
     */
    public List<Game> getAwayGamesByClubId(long clubId) {
        log.info("Finding away games for club with id: {}", clubId);
        return gameRepository.getAwayGamesByClubId(clubId);
    }

    /**
     * The method saves information about a scored goal
     * @param goal goal DTO
     */
    public void goal(GoalDTO goal) {
        log.info("Register goal");

        Game game = GoalMapper.toGame(goal);
        PlayerStatisticsDTO playerStatistics = GoalMapper.toPlayerStatisticsDTO(goal);

        game = incrementScore(game);
        update(game);

        log.info("Send player statistics to Kafka");
        playerService.sendStatistics(playerStatistics);
    }

    /**
     * The method receives a game with a modified score, Finding a game with an identical game id,
     * Finding sum home club scores, Finding sum away club scores,
     * set these values in game and return updated game
     * @param gameNewScore game with score changes
     * @return game with updated score
     */
    public Game incrementScore(Game gameNewScore) {
        log.info("Incrementing score");
        Game game = findById(gameNewScore.getId()).orElseThrow(() -> new GameNotFoundException("Game not found"));

        int homeClubScore = game.getHomeClubScore() + gameNewScore.getHomeClubScore();
        int awayClubScore = game.getAwayClubScore() + gameNewScore.getAwayClubScore();

        game.setHomeClubScore(homeClubScore);
        game.setAwayClubScore(awayClubScore);

        return game;
    }

    /**
     * The method saves a game to the database
     * @param game game for saving
     */
    public void save(Game game) {
        log.info("Save gam");
        gameRepository.save(game);
    }

    /**
     * The method updates a game record by game id in the database
     * @param game game for updating
     */
    @Transactional
    public void update(Game game) {
        log.info("Update game with id: {}", game.getId());
        gameRepository.save(game);
    }

    /**
     * The method gets a list of won games by the club id
     * @param clubId club id
     * @return a list of win games
     */
    public List<Game> getWinGamesByClubId(long clubId) {
        log.info("Finding win games for club with id: {}", clubId);
        return gameRepository.getWinGamesByClubId(clubId);
    }

    /**
     * The method gets a list of lost games by the club id
     * @param clubId club id
     * @return a list of lost games
     */
    public List<Game> getLoseGamesByClubId(long clubId) {
        log.info("Finding lose games for club with id: {}", clubId);
        return gameRepository.getLoseGamesByClubId(clubId);
    }

    /**
     * The method deletes a game record by game id from the database
     * @param id game id
     */
    public void delete(long id) {
        log.info("Delete game by id: {}", id);
        gameRepository.deleteById(id);
    }
}
