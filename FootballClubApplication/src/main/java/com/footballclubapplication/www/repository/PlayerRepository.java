package com.footballclubapplication.www.repository;

import com.footballclubapplication.www.entity.Club;
import com.footballclubapplication.www.entity.Player;
import com.footballclubapplication.www.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
