package com.footballclab.www.repository;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Player;
import com.footballclab.www.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("UPDATE Player SET club= :club, name= :name , position= :position WHERE id= :id")
    @Modifying
    void update(long id, Club club, String name, Position position);
}
