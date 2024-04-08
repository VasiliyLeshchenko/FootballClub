package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.dto.mapper.PlayerMapper;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclubapplication.www.service.PlayerService;
import com.footballclubapplication.www.service.PlayerStatisticsProducer;
//import statistics.entity.PlayerStatistics;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @GetMapping
    public List<Player> findAll() {
        logger.info("Finding all players");
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") long id) {
        logger.info("Finding player by id: {}", id);
        return playerService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Player with id {} not found", id);
                    return new PlayerNotFoundException("Player not found");
                });
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody PlayerDTO playerDTO) {

        playerService.save(PlayerMapper.INSTANCE.toPlayer(playerDTO));
        return ResponseEntity.ok("The player has been saved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PlayerDTO playerDTO) {
        logger.info("Update player by id: {}", playerDTO.getId());
        playerService.update(PlayerMapper.INSTANCE.toPlayer(playerDTO));
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeClub(
            @PathVariable("id") long id,
            @RequestParam("newClubId") long clubId
    ) {
        logger.info("Transfer player with id: {} to club with id: {}", id, clubId);
        playerService.changeClub(id, clubId);
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        logger.info("Delete player by id: {}", id);
        playerService.delete(id);
        return ResponseEntity.ok()
                .body("The player has been successfully deleted");
    }

}
