package com.footballclubapplication.www.service;

import com.footballclubapplication.www.entity.Club;
import com.footballclubapplication.www.entity.Player;
import com.footballclubapplication.www.exeption.PlayerNotFoundException;
import com.footballclubapplication.www.repository.ClubRepository;
import com.footballclubapplication.www.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
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

    @Transactional
    public void changeClub(long playerId, long newClubId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        Club newClub = clubRepository.findById(newClubId)
                .orElseThrow(() -> new PlayerNotFoundException("Club not found"));

        player.setClub(newClub);

        playerRepository.save(player);
    }

    public void delete(long id) {
        playerRepository.deleteById(id);
    }
}
