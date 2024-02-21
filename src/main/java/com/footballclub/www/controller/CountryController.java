package com.footballclub.www.controller;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Country;
import com.footballclub.www.entity.Player;
import com.footballclub.www.exeption.CountryNotFoundException;
import com.footballclub.www.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;


    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") long id) {
        return countryService.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));
    }

    @GetMapping("/{id}/clubs")
    public List<Club> clubsByCountryId(@PathVariable("id") long id) {
        return countryService.findClubsByCountryId(id);
    }

    @GetMapping("/{id}/players")
    public List<Player> playersByCountryId(@PathVariable("id") long id) {
        return countryService.findPlayersByCountryId(id);
    }

    @PostMapping
    public void saveCountry(
            @RequestParam("name") String name) {
        Country newCountry = new Country(name);
        countryService.save(newCountry);
    }

    @PutMapping("/{id}")
    public void changeName(@RequestBody Country country) {
        countryService.update(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") long id) {
        countryService.delete(id);
    }



}
