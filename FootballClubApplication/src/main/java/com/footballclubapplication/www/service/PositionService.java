package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import com.footballclubapplication.www.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public Optional<Position> findById(long id) {
        return positionRepository.findById(id);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public List<Player> getPlayersByPositionId(long id) {
        return positionRepository.getPlayersByPositionId(id);
    }

    public void save(Position position) {
        positionRepository.save(position);
    }

    @Transactional
    public void update(Position position) {
        positionRepository.save(position);
    }

    public void delete(long id) {
        positionRepository.deleteById(id);
    }
}
