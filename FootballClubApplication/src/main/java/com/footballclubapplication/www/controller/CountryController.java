package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.CountryDTO;
import com.footballclub.core.dto.mapper.CountryMapper;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.CountryNotFoundException;
import com.footballclubapplication.www.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

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

    /*@PostMapping
    public void saveCountry(
            @RequestParam("name") String name) {
        Country newCountry = new Country(name);
        countryService.save(newCountry);
    }*/

    @PostMapping
    public void saveCountry(
            @RequestBody CountryDTO countryDTO) {
        Country newCountry = CountryMapper.INSTANCE.toCountry(countryDTO);
        countryService.save(newCountry);
    }

    @PutMapping("/{id}")
    public void changeName(@RequestBody CountryDTO countryDTO) {
        Country country = CountryMapper.INSTANCE.toCountry(countryDTO);
        countryService.update(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") long id) {
        countryService.delete(id);
    }



}
