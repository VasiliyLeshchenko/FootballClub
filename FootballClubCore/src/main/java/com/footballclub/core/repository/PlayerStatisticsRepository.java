package com.footballclub.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.footballclub.core.entity.PlayerStatistics;
import org.springframework.data.jpa.repository.Query;

public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics, Long> {
    @Query("SELECT ps FROM PlayerStatistics ps WHERE ps.player.id= :playerId AND ps.club.id= :clubId")
    PlayerStatistics findByPlayerAndClubId(long playerId, long clubId);
}
