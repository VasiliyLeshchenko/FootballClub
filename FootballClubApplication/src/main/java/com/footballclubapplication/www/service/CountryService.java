package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Player;
import com.footballclub.core.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the country class with property: <b>countryRepository</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class CountryService {

    /** Club repository property */
    private final CountryRepository countryRepository;

    /**
     * The method gets a list of all countries from the database
     * @return a list of all countries
     */
    public List<Country> findAll() {
        log.info("Finding all countries");
        return countryRepository.findAll();
    }

    /**
     * The method gets an optional country value by country id
     * @param id country id
     * @return an optional country value
     */
    public Optional<Country> findById(long id) {
        log.info("Finding country by id: {}", id);
        return countryRepository.findById(id);
    }

    /**
     * The method gets a list of clubs from one country by country id
     * @param id country id
     * @return a list of clubs
     */
    public List<Club> findClubsByCountryId(long id) {
        log.info("Finding clubs by country id: {}", id);
        return countryRepository.findClubsByCountryId(id);
    }

    /**
     * The method gets a list of players from one country by country id
     * @param id country id
     * @return a list of players
     */
    public List<Player> findPlayersByCountryId(long id) {
        log.info("Finding players by country id: {}", id);
        return countryRepository.findPlayersByCountryId(id);
    }

    /**
     * The method saves a country to the database
     * @param country country for saving
     */
    public void save(Country country) {
        log.info("Save country");
        countryRepository.save(country);
    }

    /**
     * The method deletes a country record by country id from the database
     * @param id country id
     */
    public void delete(long id) {
        log.info("Delete country by id: {}", id);
        countryRepository.deleteById(id);
    }

    /**
     * The method updates a country record by country id in the database
     * @param country country for updating
     */
    @Transactional
    public void update(Country country) {
        log.info("Update country with id: {}", country.getId());
        countryRepository.save(country);
    }
}
