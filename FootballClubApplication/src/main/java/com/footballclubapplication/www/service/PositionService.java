package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import com.footballclub.core.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service class for the position class with property: <b>positionRepository</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class PositionService {
    /** Player repository property */
    private final PositionRepository positionRepository;

    /**
     * The method gets an optional position value by position id
     * @param id position id
     * @return an optional country value
     */
    public Optional<Position> findById(long id) {
        log.info("Finding position by id: {}", id);
        return positionRepository.findById(id);
    }

    /**
     * The method gets a list of all positions from the database
     * @return a list of all positions
     */
    public List<Position> findAll() {
        log.info("Finding all positions");
        return positionRepository.findAll();
    }

    /**
     * The method gets a list of one position players playing in the same position from the database
     * @return a list of players
     */
    public List<Player> getPlayersByPositionId(long id) {
        log.info("Get players by position id: {}", id);
        return positionRepository.getPlayersByPositionId(id);
    }

    /**
     * The method saves a position to the database
     * @param position position for saving
     */
    public void save(Position position) {
        log.info("Save position: {}", position);
        positionRepository.save(position);
    }

    /**
     * The method updates a position record by position id in the database
     * @param position position for updating
     */
    @Transactional
    public void update(Position position) {
        log.info("Update position: {}", position);
        positionRepository.save(position);
    }

    /**
     * The method deletes a position record by position id from the database
     * @param id position id
     */
    public void delete(long id) {
        log.info("Delete position by id: {}", id);
        positionRepository.deleteById(id);
    }
}
