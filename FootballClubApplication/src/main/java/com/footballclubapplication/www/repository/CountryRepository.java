package com.footballclubapplication.www.repository;

import com.footballclubapplication.www.entity.Club;
import com.footballclubapplication.www.entity.Country;
import com.footballclubapplication.www.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT c FROM Club c JOIN Country co ON c.country.id= co.id WHERE co.id= :id")
    List<Club> findClubsByCountryId(long id);

    @Query("SELECT p FROM Player p JOIN Country co ON p.citizenship.id=co.id WHERE co.id= :id")
    List<Player> findPlayersByCountryId(long id);
}
