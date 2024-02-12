package com.footballclub.www.service;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Country;
import com.footballclub.www.entity.Player;
import com.footballclub.www.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Optional<Country> findById(long id) {
        return countryRepository.findById(id);
    }

    public void save(Country country) {
        countryRepository.save(country);
    }

    public void delete(long id) {
        countryRepository.deleteById(id);
    }

    @Transactional
    public void changeName(long id, String newName) {
        countryRepository.changeName(id, newName);
    }

    public List<Club> findClubsByCountryId(long id) {
        return countryRepository.findClubsByCountryId(id);
    }

    public List<Player> findPlayersByCountryId(long id) {
        return countryRepository.findPlayersByCountryId(id);
    }
}
