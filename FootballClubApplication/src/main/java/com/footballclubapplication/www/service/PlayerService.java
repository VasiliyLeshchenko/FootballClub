package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.ClubNotFoundException;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclub.core.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.footballclub.core.dto.PlayerStatisticsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the player class with properties:
 * <b>playerRepository</b>, <b>clubService</b>, <b>statisticsProducer</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
public class PlayerService {
    /** Player repository property */
    private final PlayerRepository playerRepository;
    /** Club service property */
    private final ClubService clubService;
    /** Player statistics producer property */
    private final PlayerStatisticsProducer statisticsProducer;
    private final Logger logger = LoggerFactory.getLogger(PlayerService.class);


    /**
     * The method gets a list of all players from the database
     * @return a list of all players
     */
    public List<Player> findAll() {
        logger.info("Find all players");
        return playerRepository.findAll();
    }

    /**
     * The method gets an optional player value by the player id
     * @param id player id
     * @return an optional player value
     */
    public Optional<Player> findById(long id) {
        logger.info("Find player by id: {}", id);
        return playerRepository.findById(id);
    }

    /**
     * The method saves a player to the database
     * @param player player for saving
     */
    public void save(Player player) {
        logger.info("Save player: {}", player);
        playerRepository.save(player);
    }

    /**
     * The method updates a player record by the player id in the database
     * @param player player for updating
     */
    @Transactional
    public void update(Player player) {
        logger.info("Update player: {}", player);
        playerRepository.save(player);
    }

    /**
     * The method changes a player's club
     * find player by playerId, find new club by newClubId
     * set new club to a player, saves changes
     * @param playerId player id
     * @param newClubId new club id
     */
    @Transactional
    public void changeClub(long playerId, long newClubId) {
        logger.info("Transfer player with id: {} to new club: {}", playerId, newClubId);
        Player player = findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        Club newClub = clubService.findById(newClubId)
                .orElseThrow(() -> new ClubNotFoundException("Club not found"));

        player.setClub(newClub);

        logger.info("Player updated: {}", player);
        save(player);
    }

    /**
     * The method deletes a player record by player id from the database
     * @param id player id
     */
    public void delete(long id) {
        logger.info("Delete player by id: {}", id);
        playerRepository.deleteById(id);
    }

    /**
     * The method sends player statistics to Kafka
     * @see com.footballclubapplication.www.producer.CustomProducer
     * @param statistics statistics DTO
     */
    public void sendStatistics(PlayerStatisticsDTO statistics) {
        logger.info("Send player statistics to Kafka: {}", statistics);
        statisticsProducer.sendStatistics(statistics);
    }
}
