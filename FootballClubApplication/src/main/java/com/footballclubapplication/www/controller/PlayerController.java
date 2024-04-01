package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.dto.mapper.PlayerMapper;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclubapplication.www.service.PlayerService;
import com.footballclubapplication.www.service.PlayerStatisticsProducer;
//import statistics.entity.PlayerStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerStatisticsProducer playerStatisticsProducer;

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
    public ResponseEntity<String> savePlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.save(PlayerMapper.INSTANCE.toPlayer(playerDTO));
        return ResponseEntity.ok("The player has been saved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.update(PlayerMapper.INSTANCE.toPlayer(playerDTO));
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
