package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Country;
import com.footballclub.core.entity.Player;
import com.footballclub.core.repository.ClubRepository;
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
class ClubServiceTest {
    @InjectMocks
    private ClubService clubService;
    @Mock
    private ClubRepository clubRepository;

    @Test
    void returnAllClubs() {
        Club club1 = new Club();
        Club club2 = new Club();
        List<Club> expectedClubs = List.of(club1, club2);
        Mockito.when(clubRepository.findAll()).thenReturn(expectedClubs);

        List<Club> actualClubs = clubService.findAll();
        System.out.println(actualClubs);

        assertEquals(expectedClubs, actualClubs);
    }

    @Test
    void returnOptionalClubById() {
        long clubId = 3L;
        Club club = new Club();
        club.setId(clubId);
        Optional<Club> expectedResult = Optional.of(club);
        Mockito.when(clubRepository.findById(clubId)).thenReturn(expectedResult);

        Optional<Club> actualResult = clubService.findById(3L);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void saveClubInDatabase() {
        Country country = new Country(3);
        Club club = new Club();
        club.setName("New football club");
        club.setCountry(country);

        clubService.save(club);

        Mockito.verify(clubRepository, Mockito.times(1)).save(club);
    }

    @Test
    void updateClubInDatabase() {
        Country country = new Country(3);
        Club club = new Club();
        club.setName("Updated football club");
        club.setCountry(country);

        clubService.update(club);

        Mockito.verify(clubRepository, Mockito.times(1)).save(club);
    }

    @Test
    void deleteClubById() {
        long clubId = 3L;
        clubService.delete(clubId);

        Mockito.verify(clubRepository, Mockito.times(1)).deleteById(clubId);
    }

    @Test
    void returnPlayerByClubId() {
        long clubId = 3L;
        Club club = new Club();
        club.setId(clubId);
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setClub(club);
        player2.setClub(club);
        List<Player> expectedPlayers = List.of(player1, player2);
        Mockito.when(clubRepository.getPlayerByClubId(clubId)).thenReturn(expectedPlayers);

        List<Player> actualPlayers = clubService.getPlayersByClubId(clubId);

        assertEquals(expectedPlayers, actualPlayers);
    }
}