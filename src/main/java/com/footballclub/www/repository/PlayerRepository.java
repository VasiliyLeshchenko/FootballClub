package com.footballclub.www.repository;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Player;
import com.footballclub.www.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Modifying
    @Query("UPDATE Player SET club= :club, name= :name, position= :position WHERE id= :id")
    void update(long id, Club club, String name, Position position);
}
