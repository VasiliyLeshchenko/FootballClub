package com.footballclab.www.service;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import com.footballclab.www.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Club> findClubsByCountryId(long id) {
        return countryRepository.findClubsByCountryId(id);
    }
}
