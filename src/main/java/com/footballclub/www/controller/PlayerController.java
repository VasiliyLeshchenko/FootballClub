package com.footballclub.www.controller;

import com.footballclub.www.entity.Player;
import com.footballclub.www.exeption.PlayerNotFoundException;
import com.footballclub.www.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") long id) {
        return playerService.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
        playerService.update(player);
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> savePlayer(@RequestBody Player player) {
        playerService.save(player);
        return ResponseEntity.ok("The player has been saved");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") long id) {
        playerService.delete(id);
        return ResponseEntity.ok()
                .body("The player has been successfully deleted");
    }

}
