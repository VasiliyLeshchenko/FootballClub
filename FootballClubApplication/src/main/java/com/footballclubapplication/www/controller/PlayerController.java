package com.footballclubapplication.www.controller;

import com.footballclubapplication.www.entity.Player;
import com.footballclubapplication.www.exeption.PlayerNotFoundException;
import com.footballclubapplication.www.service.PlayerService;
import com.footballclubapplication.www.service.PlayerStatisticsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerStatisticsProducer playerStatisticsProducer;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerStatisticsProducer playerStatisticsProducer) {
        this.playerService = playerService;
        this.playerStatisticsProducer = playerStatisticsProducer;
    }

    @PostMapping("/send")
    public void sendStatistics(@RequestBody String message) {
        playerStatisticsProducer.sendMessage(message);
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

    @PostMapping
    public ResponseEntity<String> savePlayer(@RequestBody Player player) {
        playerService.save(player);
        return ResponseEntity.ok("The player has been saved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
        playerService.update(player);
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeClub(
            @PathVariable("id") long id,
            @RequestParam("newClubId") long clubId
    ) {
        playerService.changeClub(id, clubId);
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") long id) {
        playerService.delete(id);
        return ResponseEntity.ok()
                .body("The player has been successfully deleted");
    }

}
