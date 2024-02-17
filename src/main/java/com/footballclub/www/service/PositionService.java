package com.footballclub.www.service;

import com.footballclub.www.entity.Player;
import com.footballclub.www.entity.Position;
import com.footballclub.www.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Optional<Position> findById(long id) {
        return positionRepository.findById(id);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public List<Player> getPlayersByPositionId(long id) {
        return positionRepository.getPlayersByPositionId(id);
    }
}
