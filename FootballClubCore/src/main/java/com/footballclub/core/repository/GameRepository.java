package com.footballclub.core.repository;

import com.footballclub.core.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE g.homeClub.id= :clubId")
    List<Game> getHomeGamesByClubId(long clubId);

    @Query("SELECT g FROM Game g WHERE g.awayClub.id= :clubId")
    List<Game> getAwayGamesByClubId(long clubId);

    @Query("SELECT g FROM Game g WHERE (g.homeClub.id= :clubId AND g.homeClubScore>g.awayClubScore) OR (g.awayClub.id= :clubId AND g.awayClubScore>g.homeClubScore)")
    List<Game> getWinGamesByClubId(long clubId);

    @Query("SELECT g FROM Game g WHERE (g.homeClub.id= :clubId AND g.homeClubScore<g.awayClubScore) OR (g.awayClub.id= :clubId AND g.awayClubScore<g.homeClubScore)")
    List<Game> getLoseGamesByClubId(long clubId);

}
