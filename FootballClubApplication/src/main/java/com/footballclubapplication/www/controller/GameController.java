package com.footballclubapplication.www.controller;

//TODO: Add code for with Game entity

import com.footballclub.core.dto.GameDTO;
import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.dto.mapper.GameMapper;
import com.footballclub.core.entity.Game;
import com.footballclub.core.exception.GameNotFoundException;
import com.footballclubapplication.www.service.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    @GetMapping
    public List<Game> findAll() {
        logger.info("Finding all games");
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable("id") long id) {
        logger.info("Finding game by id: {}", id);
        return gameService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Game with id {} not found", id);
                    return new GameNotFoundException("Game not found");
                });
    }

    //TODO: новое
    @PostMapping("/goal")
    public ResponseEntity<Void> registerGoal(@RequestBody GoalDTO goal) {
        logger.info("Register goal: {}", goal);
        gameService.goal(goal);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody GameDTO gameDTO) {
        logger.info("Save game: {}", gameDTO);
        gameService.save(GameMapper.INSTANCE.toGame(gameDTO));
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody GameDTO gameDTO) {
        logger.info("Update game: {}", gameDTO);
        gameService.update(GameMapper.INSTANCE.toGame(gameDTO));
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        logger.info("Delete game by id {}", id);
        gameService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
