package com.footballclubapplication.www.controller;

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
    public void  saveClub(@RequestBody ClubDTO clubDTO) {
        Country country = countryService.findById(clubDTO.getCountry().getId())
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));

        Club newClub = ClubMapper.INSTANCE.toClub(clubDTO);

        clubService.save(newClub);
    }


    //TODO: FINISH SEARCH CLUBS BY NAME
    /*@GetMapping
    public List<Club> findClubsByName(@RequestParam("clubName") String clubName) {
        return clubService.findByName(clubName);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClub(@RequestBody ClubDTO clubDTO) {
        clubService.update(ClubMapper.INSTANCE.toClub(clubDTO));
        return ResponseEntity
                .ok("Club updated successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable("id") long id) {
        clubService.delete(id);
    }
}
