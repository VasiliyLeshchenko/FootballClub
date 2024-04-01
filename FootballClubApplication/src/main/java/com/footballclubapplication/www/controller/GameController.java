package com.footballclubapplication.www.controller;

//TODO: Add code for with Game entity

import com.footballclub.core.dto.GameDTO;
import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.dto.mapper.GameMapper;
import com.footballclub.core.entity.Game;
import com.footballclub.core.exception.GameNotFoundException;
import com.footballclubapplication.www.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable("id") long id) {
        return gameService.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    //TODO: новое
    @PostMapping("/goal")
    public ResponseEntity<Void> goal(@RequestBody GoalDTO goal) {
        gameService.goal(goal);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping
    public ResponseEntity<String> saveGame(@RequestBody GameDTO gameDTO) {
        gameService.save(GameMapper.INSTANCE.toGame(gameDTO));
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@RequestBody GameDTO gameDTO) {
        gameService.update(GameMapper.INSTANCE.toGame(gameDTO));
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable("id") long id) {
        gameService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
