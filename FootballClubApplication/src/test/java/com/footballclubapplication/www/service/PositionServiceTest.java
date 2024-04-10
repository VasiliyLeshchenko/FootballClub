package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import com.footballclub.core.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PositionServiceTest {
    @InjectMocks
    private PositionService positionService;
    @Mock
    private PositionRepository positionRepository;

    @Test
    void returnOptionalPositionById() {
        long positionId = 1L;
        Position position = new Position(positionId);
        Optional<Position> expectedResult = Optional.of(position);
        Mockito.when(positionRepository.findById(positionId)).thenReturn(expectedResult);

        Optional<Position> actualResult = positionService.findById(positionId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findAll() {
        Position position1 = new Position();
        Position position2 = new Position();
        List<Position> expectedResult = List.of(position1, position2);
        Mockito.when(positionRepository.findAll()).thenReturn(expectedResult);

        List<Position> actualResult = positionService.findAll();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPlayersByPositionId() {
        long positionId = 1L;
        Position position = new Position(positionId);
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setPosition(position);
        player2.setPosition(position);
        List<Player> expectedResult = List.of(player1, player2);
        Mockito.when(positionRepository.getPlayersByPositionId(positionId)).thenReturn(expectedResult);

        List<Player> actualResult = positionService.getPlayersByPositionId(positionId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void save() {
        Position position = new Position();
        position.setName("New position");

        positionService.save(position);

        Mockito.verify(positionRepository, Mockito.times(1)).save(position);
    }

    @Test
    void update() {
        Position position = new Position(1L, "Updated position");

        positionService.update(position);

        Mockito.verify(positionRepository, Mockito.times(1)).save(position);
    }

    @Test
    void delete() {
        long positionId = 1L;

        positionService.delete(positionId);

        Mockito.verify(positionRepository, Mockito.times(1)).deleteById(positionId);
    }
}