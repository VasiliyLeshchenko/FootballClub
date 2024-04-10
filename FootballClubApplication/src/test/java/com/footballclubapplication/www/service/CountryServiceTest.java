package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Player;
import com.footballclub.core.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    @InjectMocks
    private CountryService countryService;
    @Mock
    private CountryRepository countryRepository;

    @Test
    void returnAllCountries() {
        Country country1 = new Country();
        Country country2 = new Country();
        List<Country> expectedCountries = List.of(country1, country2);
        Mockito.when(countryRepository.findAll()).thenReturn(expectedCountries);

        List<Country> actualCountries = countryService.findAll();

        assertEquals(expectedCountries, actualCountries);
    }
    @Test
    void returnOptionalCountryById() {
        long countryId = 1L;
        Country country = new Country(countryId);
        Optional<Country> expectedResult = Optional.of(country);
        Mockito.when(countryRepository.findById(countryId)).thenReturn(expectedResult);

        Optional<Country> actualResult = countryService.findById(countryId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void returnClubsByCountryId() {
        long countryId = 1L;
        Country country = new Country(countryId);
        Club club1 = new Club();
        Club club2 = new Club();
        club1.setCountry(country);
        club2.setCountry(country);
        List<Club> expectedClubs = List.of(club1, club2);
        Mockito.when(countryRepository.findClubsByCountryId(countryId)).thenReturn(expectedClubs);

        List<Club> actualClubs = countryService.findClubsByCountryId(countryId);

        assertEquals(expectedClubs, actualClubs);
    }

    @Test
    void returnPlayersByCountryId() {
        long countryId = 1L;
        Country country = new Country(countryId);
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setCitizenship(country);
        player2.setCitizenship(country);
        List<Player> expectedPlayers = List.of(player1, player2);
        Mockito.when(countryRepository.findPlayersByCountryId(countryId)).thenReturn(expectedPlayers);

        List<Player> actualPlayers = countryService.findPlayersByCountryId(countryId);

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    void saveCountryInDatabase() {
        Country country = new Country(3L);
        country.setName("New country");

        countryService.save(country);

        Mockito.verify(countryRepository, Mockito.times(1)).save(country);
    }

    @Test
    void updateCountryInDatabase() {
        Country country = new Country(3L);
        country.setName("New country");

        countryService.update(country);

        Mockito.verify(countryRepository, Mockito.times(1)).save(country);
    }

    @Test
    void deleteCountryById() {
        long countryId = 3L;
        countryService.delete(countryId);

        Mockito.verify(countryRepository, Mockito.times(1)).deleteById(countryId);
    }
}