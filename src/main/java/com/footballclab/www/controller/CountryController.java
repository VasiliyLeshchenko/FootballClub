package com.footballclab.www.controller;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import com.footballclab.www.exeption.CountryNotFoundException;
import com.footballclab.www.service.CountryService;
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

    @PostMapping
    public void saveCountry(
            @RequestParam("name") String name) {
        Country newCountry = new Country(name);
        countryService.save(newCountry);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") long id) {
        countryService.delete(id);
    }

    @GetMapping("/{id}/clubs")
    public List<Club> clubsByCountryId(@PathVariable("id") long id) {
        return countryService.findClubsByCountryId(id);
    }

    @PutMapping("/{id}")
    public void changeName(
            @PathVariable("id") long id,
            @RequestBody Map<String, String> request
            ) {
        String newName = request.get("newName");
        countryService.changeName(id, newName);
    }
}
