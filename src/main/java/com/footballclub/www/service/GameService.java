package com.footballclub.www.service;

import com.footballclub.www.entity.Game;
import com.footballclub.www.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<Game> findById(long id) {
        return gameRepository.findById(id);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }
    public List<Game> getHomeGamesByClubId(long clubId) {
        return gameRepository.getHomeGamesByClubId(clubId);
    }

    public List<Game> getAwayGamesByClubId(long clubId) {
        return gameRepository.getAwayGamesByClubId(clubId);
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    @Transactional
    public void update(Game game) {
        gameRepository.save(game);
    }

    public List<Game> getWinGamesByClubId(long clubId) {
        return gameRepository.getWinGamesBy(clubId);
    }

    public List<Game> getLoseGamesByClubId(long clubId) {
        return gameRepository.getLoseGamesBy(clubId);
    }

    public void delete(long id) {
        gameRepository.deleteById(id);
    }
}
