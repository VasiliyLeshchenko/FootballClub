package com.footballclubapplication.www.repository;

import com.footballclub.core.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
