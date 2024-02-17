package com.footballclub.www.repository;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    @Modifying
    @Query("UPDATE Club SET name= :clubName, country.id= :countryId WHERE id= :clubId")
    void update(long clubId, String clubName, long countryId);

    @Modifying
    @Query("SELECT p FROM Player p JOIN Club c ON p.club.id=c.id WHERE c.id= :id")
    List<Player> getPlayerByClubId(long id);
}
