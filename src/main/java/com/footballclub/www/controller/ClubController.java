package com.footballclub.www.controller;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Country;
import com.footballclub.www.entity.Game;
import com.footballclub.www.entity.Player;
import com.footballclub.www.exeption.ClubNotFoundException;
import com.footballclub.www.exeption.CountryNotFoundException;
import com.footballclub.www.service.ClubService;
import com.footballclub.www.service.CountryService;
import com.footballclub.www.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: Add Get request for games
@RestController
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;
    private final CountryService countryService;
    private final GameService gameService;

    @Autowired
    public ClubController(ClubService clubService, CountryService countryService, GameService gameService) {
        this.clubService = clubService;
        this.countryService = countryService;
        this.gameService = gameService;
    }

    @GetMapping
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public Club findById(@PathVariable("id") long id) {
        return clubService.findById(id)
                .orElseThrow(() -> new ClubNotFoundException("Club not found"));
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByClubId(@PathVariable("id") long id) {
        return clubService.getPlayersByClubId(id);
    }

    @GetMapping("/{id}/home-games")
    public List<Game> getHomeGames(@PathVariable("id") long id) {
        return gameService.getHomeGamesByClubId(id);
    }

    @GetMapping("/{id}/away-games")
    public List<Game> getAwayGames(@PathVariable("id") long id) {
        return gameService.getAwayGamesByClubId(id);
    }

    @GetMapping("/{id}/win-games")
    public List<Game> getWinGames(@PathVariable("id") long id) {
        return gameService.getWinGamesByClubId(id);
    }

    @GetMapping("/{id}/lose-games")
    public List<Game> getLoseGames(@PathVariable("id") long id) {
        return gameService.getLoseGamesByClubId(id);
    }

    @PostMapping
    public void  saveClub(
            @RequestParam("clubName") String clubName,
            @RequestParam("countryId") long countryId
    ) {
        Country country = countryService.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));

        Club newClub = new Club(clubName);
        newClub.setCountry(country);

        clubService.save(newClub);
    }

   /* @GetMapping
    public List<Club> findClubsByName(@RequestParam("clubName") String clubName) {
        return clubService.findByName(clubName);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClub(@RequestBody Club club) {
        clubService.update(club);

        return ResponseEntity
                .ok("Club updated successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable("id") long id) {
        clubService.delete(id);
    }
}
