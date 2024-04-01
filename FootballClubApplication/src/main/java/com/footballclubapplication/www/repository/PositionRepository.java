package com.footballclubapplication.www.repository;

import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("SELECT p FROM Player p JOIN Position po ON p.position.id= po.id WHERE po.id= :id")
    List<Player> getPlayersByPositionId(long id);
}
