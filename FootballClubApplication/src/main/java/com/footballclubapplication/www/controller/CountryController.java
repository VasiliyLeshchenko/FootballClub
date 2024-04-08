package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.CountryDTO;
import com.footballclub.core.dto.mapper.CountryMapper;
import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.CountryNotFoundException;
import com.footballclubapplication.www.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @GetMapping
    public List<Country> findAll() {
        logger.info("Finding all countries");
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") long id) {
        logger.info("Finding country by id: {}", id);
        return countryService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Country with id {} not found", id);
                    return new CountryNotFoundException("Country not found");
                });
    }

    @GetMapping("/{id}/clubs")
    public List<Club> findClubsByCountryId(@PathVariable("id") long id) {
        logger.info("Finding clubs by country id: {}", id);
        return countryService.findClubsByCountryId(id);
    }

    @GetMapping("/{id}/players")
    public List<Player> findPlayersByCountryId(@PathVariable("id") long id) {
        logger.info("Finding players by country id: {}", id);
        return countryService.findPlayersByCountryId(id);
    }

    @PostMapping
    public void save(
            @RequestBody CountryDTO countryDTO) {
        logger.info("Save country: {}", countryDTO);
        Country newCountry = CountryMapper.INSTANCE.toCountry(countryDTO);
        countryService.save(newCountry);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CountryDTO countryDTO) {
        logger.info("Update country: {}", countryDTO);
        Country country = CountryMapper.INSTANCE.toCountry(countryDTO);
        countryService.update(country);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        logger.info("Delete country by id: {}", id);
        countryService.delete(id);
    }



}
