package com.footballclab.www.controller;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import com.footballclab.www.entity.Player;
import com.footballclab.www.exeption.CountryNotFoundException;
import com.footballclab.www.exeption.PlayerNotFoundException;
import com.footballclab.www.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

   /* @GetMapping("/{id}/clubs")
    public List<Club> clubsByCountryId(@PathVariable("id") long id) {
        return playerService.findClubsByCountryId(id);
    }

    @PostMapping
    public void saveCountry(
            @RequestParam("name") String name) {
        Country newCountry = new Country(name);
        playerService.save(newCountry);
    }

    @PutMapping("/{id}")
    public void changeName(
            @PathVariable("id") long id,
            @RequestBody Map<String, String> request
    ) {
        String newName = request.get("newName");
        playerService.changeName(id, newName);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") long id) {
        playerService.delete(id);
    }
*/

}
