package com.footballclub.www.controller;

//TODO: Add code for with Game entity

import com.footballclub.www.entity.Game;
import com.footballclub.www.exeption.GameNotFoundException;
import com.footballclub.www.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable("id") long id) {
        return gameService.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    /*@GetMapping("/club/{club_id}/home-games")
    public List<Game> getHomeGamesByClubId(@PathVariable("club_id") long clubId) {
        return gameService.getHomeGamesByClubId(clubId);
    }*/

//    @GetMapping("/club/{club_id}/away-games")
//    public List<Game> getAwayGamesByClubId(@PathVariable("club_id") long clubId) {
//        return gameService.getAwayGamesByClubId(clubId);
//    }

    @PostMapping
    public ResponseEntity<String> saveGame(@RequestBody Game game) {
        gameService.save(game);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@RequestBody Game game) {
        gameService.update(game);
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
