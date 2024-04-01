package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclub.core.exception.PlayerNotFoundException;
import com.footballclubapplication.www.repository.ClubRepository;
import com.footballclubapplication.www.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.footballclub.core.dto.PlayerStatisticsDTO;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final PlayerStatisticsProducer statisticsProducer;

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

    public void sendStatistics(PlayerStatisticsDTO statistics) {
        statisticsProducer.sendStatistics(statistics);
    }
}
