package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.ClubDTO;
import com.footballclub.core.dto.mapper.ClubMapper;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Game;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.ClubNotFoundException;
import com.footballclubapplication.www.service.ClubService;
import com.footballclubapplication.www.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final GameService gameService;

    @GetMapping
    public List<Club> findAll() {
        log.info("Finding all clubs");
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public Club findById(@PathVariable("id") long id) {
        log.info("Finding club by id: {}", id);
        return clubService.findById(id)
                .orElseThrow(() -> {
                    log.error("Club with id {} not found", id);
                    return new ClubNotFoundException("Club not found");
                });
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByClubId(@PathVariable("id") long id) {
        log.info("Finding players by club id: {}", id);
        return clubService.getPlayersByClubId(id);
    }

    @GetMapping("/{id}/home-games")
    public List<Game> getHomeGames(@PathVariable("id") long id) {
        log.info("Finding home games by club id: {}", id);
        return gameService.getHomeGamesByClubId(id);
    }

    @GetMapping("/{id}/away-games")
    public List<Game> getAwayGames(@PathVariable("id") long id) {
        log.info("Finding away games by club id: {}", id);
        return gameService.getAwayGamesByClubId(id);
    }

    @GetMapping("/{id}/win-games")
    public List<Game> getWinGames(@PathVariable("id") long id) {
        log.info("Finding win games by club id: {}", id);
        return gameService.getWinGamesByClubId(id);
    }

    @GetMapping("/{id}/lose-games")
    public List<Game> getLoseGames(@PathVariable("id") long id) {
        log.info("Finding lose games by club id: {}", id);
        return gameService.getLoseGamesByClubId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping
    public void  save(@RequestBody ClubDTO clubDTO) {
        log.info("Save club with id: {}", clubDTO.getId());

        Club newClub = ClubMapper.INSTANCE.toClub(clubDTO);

        clubService.save(newClub);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody ClubDTO clubDTO) {
        log.info("Update club with id: {}", clubDTO.getId());
        clubService.update(ClubMapper.INSTANCE.toClub(clubDTO));
        return ResponseEntity
                .ok("Club updated successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        log.info("Delete club by id: {}", id);
        clubService.delete(id);
    }
}
