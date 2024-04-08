package com.footballclubapplication.www.controller;

import ch.qos.logback.classic.Level;
import com.footballclub.core.dto.ClubDTO;
import com.footballclub.core.dto.mapper.ClubMapper;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Game;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.ClubNotFoundException;
import com.footballclub.core.exception.CountryNotFoundException;
import com.footballclubapplication.www.service.ClubService;
import com.footballclubapplication.www.service.CountryService;
import com.footballclubapplication.www.service.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final CountryService countryService;
    private final GameService gameService;
    private final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @GetMapping
    public List<Club> findAll() {
        logger.info("Finding all clubs");
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public Club findById(@PathVariable("id") long id) {
        logger.info("Finding club by id: {}", id);
        return clubService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Club with id {} not found", id);
                    return new ClubNotFoundException("Club not found");
                });
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByClubId(@PathVariable("id") long id) {
        logger.info("Finding players by club id: {}", id);
        return clubService.getPlayersByClubId(id);
    }

    @GetMapping("/{id}/home-games")
    public List<Game> getHomeGames(@PathVariable("id") long id) {
        logger.info("Finding home games by club id: {}", id);
        return gameService.getHomeGamesByClubId(id);
    }

    @GetMapping("/{id}/away-games")
    public List<Game> getAwayGames(@PathVariable("id") long id) {
        logger.info("Finding away games by club id: {}", id);
        return gameService.getAwayGamesByClubId(id);
    }

    @GetMapping("/{id}/win-games")
    public List<Game> getWinGames(@PathVariable("id") long id) {
        logger.info("Finding win games by club id: {}", id);
        return gameService.getWinGamesByClubId(id);
    }

    @GetMapping("/{id}/lose-games")
    public List<Game> getLoseGames(@PathVariable("id") long id) {
        logger.info("Finding lose games by club id: {}", id);
        return gameService.getLoseGamesByClubId(id);
    }

    @PostMapping
    public void  save(@RequestBody ClubDTO clubDTO) {
        logger.info("Save club: {}", clubDTO);

        Club newClub = ClubMapper.INSTANCE.toClub(clubDTO);

        clubService.save(newClub);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody ClubDTO clubDTO) {
        logger.info("Update club: {}", clubDTO);
        clubService.update(ClubMapper.INSTANCE.toClub(clubDTO));
        return ResponseEntity
                .ok("Club updated successfully");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        logger.info("Delete club by id: {}", id);
        clubService.delete(id);
    }
}
