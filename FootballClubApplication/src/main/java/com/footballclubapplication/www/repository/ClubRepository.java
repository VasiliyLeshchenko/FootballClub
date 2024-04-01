package com.footballclubapplication.www.repository;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//TODO: DELETE SQL update query everywhere after check game update method
public interface ClubRepository extends JpaRepository<Club, Long> {
    @Query("SELECT p FROM Player p JOIN Club c ON p.club.id=c.id WHERE c.id= :id")
    List<Player> getPlayerByClubId(long id);
}
