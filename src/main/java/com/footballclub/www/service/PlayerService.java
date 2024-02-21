package com.footballclub.www.service;

import com.footballclub.www.entity.Player;
import com.footballclub.www.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(long id) {
        return playerRepository.findById(id);
    }

    public void save(Player player) {
        playerRepository.save(player);
    }

    @Transactional
    public void update(Player player) {
        playerRepository.save(player);
    }

    public void delete(long id) {
        playerRepository.deleteById(id);
    }
}
