package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.dto.mapper.PlayerMapper;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclubapplication.www.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public List<Player> findAll() {
        log.info("Finding all players");
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") long id) {
        log.info("Finding player by id: {}", id);
        return playerService.findById(id)
                .orElseThrow(() -> {
                    log.error("Player with id {} not found", id);
                    return new PlayerNotFoundException("Player not found");
                });
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody PlayerDTO playerDTO) {

        playerService.save(PlayerMapper.INSTANCE.toPlayer(playerDTO));
        return ResponseEntity.ok("The player has been saved");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PlayerDTO playerDTO) {
        log.info("Update player with id: {}", playerDTO.getId());
        playerService.update(PlayerMapper.INSTANCE.toPlayer(playerDTO));
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PatchMapping("/{id}")
    public ResponseEntity<String> changeClub(
            @PathVariable("id") long id,
            @RequestParam("newClubId") long clubId
    ) {
        log.info("Transfer player with id: {} to club with id: {}", id, clubId);
        playerService.changeClub(id, clubId);
        return new ResponseEntity<>("The player has been updated", HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        log.info("Delete player by id: {}", id);
        playerService.delete(id);
        return ResponseEntity.ok()
                .body("The player has been successfully deleted");
    }

}
