package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.ClubNotFoundException;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclub.core.repository.PlayerRepository;
import com.footballclub.core.repository.PlayerStatisticsRepository;
import com.footballclubapplication.www.event.SendStatisticsPublisher;
import com.footballclubapplication.www.producer.CustomProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class PlayerService {
    /** Player repository property */
    private final PlayerRepository playerRepository;
    /** Club service property */
    private final ClubService clubService;
    /** Player statistics producer property */
    private final CustomProducer statisticsProducer;
    private final PlayerStatisticsRepository statisticsRepository;
    private final SendStatisticsPublisher publisher;
    //private final PlayerStatisticsProducer statisticsProducer;

    /**
     * The method gets a list of all players from the database
     * @return a list of all players
     */
    public List<Player> findAll() {
        log.info("Finding all players");
        return playerRepository.findAll();
    }

    /**
     * The method gets an optional player value by the player id
     * @param id player id
     * @return an optional player value
     */
    public Optional<Player> findById(long id) {
        log.info("Finding player by id: {}", id);
        return playerRepository.findById(id);
    }

    /**
     * The method saves a player to the database
     * @param player player for saving
     */
    public void save(Player player) {
        log.info("Save player");
        playerRepository.save(player);
    }

    /**
     * The method updates a player record by the player id in the database
     * @param player player for updating
     */
    @Transactional
    public void update(Player player) {
        log.info("Update player: {}", player);
        playerRepository.save(player);
    }

    /**
     * The method changes a player's club
     * Finding player by playerId, Finding new club by newClubId
     * set new club to a player, saves changes
     * @param playerId player id
     * @param newClubId new club id
     */
    @Transactional
    public void changeClub(long playerId, long newClubId) {
        log.info("Transfer player with id: {} to new club with id: {}", playerId, newClubId);
        Player player = findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        Club newClub = clubService.findById(newClubId)
                .orElseThrow(() -> new ClubNotFoundException("Club not found"));

        player.setClub(newClub);

        update(player);
    }

    /**
     * The method deletes a player record by player id from the database
     * @param id player id
     */
    public void delete(long id) {
        log.info("Delete player by id: {}", id);
        playerRepository.deleteById(id);
    }

    /**
     * The method sends player statistics to Kafka
     * @see com.footballclubapplication.www.producer.CustomProducer
     * @param statistics statistics DTO
     */
    public void sendStatistics(PlayerStatisticsDTO statistics) {
        log.info("Send player statistics to Kafka");
        publisher.publishStatistics("Send statistics from PlayerService.sendStatics()");
        statisticsProducer.send("player.statistics.save", statistics);
        //statisticsProducer.sendStatistics(statistics);
    }
}
