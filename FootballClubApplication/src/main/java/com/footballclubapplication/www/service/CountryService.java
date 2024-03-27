package com.footballclubapplication.www.service;

import com.footballclubapplication.www.entity.Club;
import com.footballclubapplication.www.entity.Country;
import com.footballclubapplication.www.entity.Player;
import com.footballclubapplication.www.repository.CountryRepository;
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
    public void update(Country country) {
        countryRepository.save(country);
    }

    public List<Club> findClubsByCountryId(long id) {
        return countryRepository.findClubsByCountryId(id);
    }

    public List<Player> findPlayersByCountryId(long id) {
        return countryRepository.findPlayersByCountryId(id);
    }
}
