package com.footballclab.www.controller;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import com.footballclab.www.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void saveCountry(@RequestBody Country country) {
        countryService.save(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") long id) {
        countryService.delete(id);
    }

    @GetMapping("/{id}/clubs")
    public List<Club> clubsByCountryId(@PathVariable("id") long id) {
        return countryService.findClubsByCountryId(id);
    }
}
