package com.footballclubapplication.www.service;

import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.dto.mapper.GoalMapper;
import com.footballclub.core.entity.Game;
import com.footballclub.core.exception.GameNotFoundException;
import com.footballclub.core.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GameService {
    /**
     * Game repository property
     * @see GameRepository
     */
    private final GameRepository gameRepository;
    /** Player service property */
    private final PlayerService playerService;
    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    /**
     * The method gets an optional game value by game id
     * @param id game id
     * @return an optional game value
     */
    public Optional<Game> findById(long id) {
        logger.info("Finding game by id: {}", id);
        return gameRepository.findById(id);
    }

    /**
     * The method gets a list of all games from the database
     * @return a list of all games
     */
    public List<Game> findAll() {
        logger.info("Finding all games");
        return gameRepository.findAll();
    }

    /**
     * The method gets a list of home games by club id
     * @param clubId club id
     * @return a list of games
     */
    public List<Game> getHomeGamesByClubId(long clubId) {
        logger.info("Finding home games by id: {}", clubId);
        return gameRepository.getHomeGamesByClubId(clubId);
    }

    /**
     * The method gets a list of away games by club id
     * @param clubId club id
     * @return a list of games
     */
    public List<Game> getAwayGamesByClubId(long clubId) {
        logger.info("Finding away games by id: {}", clubId);
        return gameRepository.getAwayGamesByClubId(clubId);
    }

    /**
     * The method saves information about a scored goal
     * @param goal goal DTO
     */
    public void goal(GoalDTO goal) {
        logger.info("Register goal: {}", goal);

        Game game = GoalMapper.toGame(goal);
        PlayerStatisticsDTO playerStatistics = GoalMapper.toPlayerStatisticsDTO(goal);

        incrementScore(game);

        logger.info("Send player statistics to Kafka: {}", playerStatistics);
        playerService.sendStatistics(playerStatistics);
    }

    /**
     * The method receives a game with a modified score, Finding a game with an identical game id,
     * Finding sum home club scores, Finding sum away club scores,
     * set these values in game and update record in the database
     * @param gameNewScore game with score changes
     */
    public void incrementScore(Game gameNewScore) {
        Game game = findById(gameNewScore.getId()).orElseThrow(() -> new GameNotFoundException("Game not found"));

        int homeClubScore = game.getHomeClubScore() + gameNewScore.getHomeClubScore();
        int awayClubScore = game.getAwayClubScore() + gameNewScore.getAwayClubScore();

        game.setHomeClubScore(homeClubScore);
        game.setAwayClubScore(awayClubScore);

        update(game);
    }

    /**
     * The method saves a game to the database
     * @param game game for saving
     */
    public void save(Game game) {
        logger.info("Save game: {}", game);
        gameRepository.save(game);
    }

    /**
     * The method updates a game record by game id in the database
     * @param game game for updating
     */
    @Transactional
    public void update(Game game) {
        logger.info("Update game: {}", game);
        gameRepository.save(game);
    }

    /**
     * The method gets a list of won games by the club id
     * @param clubId club id
     * @return a list of win games
     */
    public List<Game> getWinGamesByClubId(long clubId) {
        logger.info("Finding win games by id: {}", clubId);
        return gameRepository.getWinGamesBy(clubId);
    }

    /**
     * The method gets a list of lost games by the club id
     * @param clubId club id
     * @return a list of lost games
     */
    public List<Game> getLoseGamesByClubId(long clubId) {
        logger.info("Finding lose games by id: {}", clubId);
        return gameRepository.getLoseGamesBy(clubId);
    }

    /**
     * The method deletes a game record by game id from the database
     * @param id game id
     */
    public void delete(long id) {
        logger.info("Delete game: {}", id);
        gameRepository.deleteById(id);
    }
}
