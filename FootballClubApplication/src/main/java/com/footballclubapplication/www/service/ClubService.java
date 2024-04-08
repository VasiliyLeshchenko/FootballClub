package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the club class with property: <b>clubRepository</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
public class ClubService {

    /** Club repository property */
    private final ClubRepository clubRepository;
    private final Logger logger = LoggerFactory.getLogger(ClubService.class);

    /**
     * The method gets a list of all clubs from the database
     * @return a list of all clubs
     */
    public List<Club> findAll() {
        logger.info("Finding all clubs");
        return clubRepository.findAll();
    }

    /**
     * The method gets an optional club value by club id
     * @param id club id
     * @return an optional club value
     */
    public Optional<Club> findById(long id) {
        logger.info("Finding club by id: {}", id);
        return clubRepository.findById(id);
    }

    /**
     * The method saves a club to the database
     * @param club club for saving to the database
     */
    public void save(Club club) {
        logger.info("Save club: {}", club);
        clubRepository.save(club);
    }

    /**
     * The method updates a club record in the database
     * @param club club for updating in the database
     */
    @Transactional
    public void update(Club club) {
        logger.info("Update club: {}", club);
        clubRepository.save(club);
    }

    /**
     * The method deletes a club record by club id from the database
     * @param id club id
     */
    public void delete(long id) {
        logger.info("Delete club by id: {}", id);
        clubRepository.deleteById(id);
    }

    /**
     * The method gets a list of club players by club id
     * @param id club id
     * @return a list of players
     */
    public List<Player> getPlayersByClubId(long id) {
        logger.info("Get players by club id: {}", id);
        return clubRepository.getPlayerByClubId(id);
    }
}
