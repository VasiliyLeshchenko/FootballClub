package com.footballclub.www.service;

import com.footballclub.www.entity.Club;
import com.footballclub.www.entity.Player;
import com.footballclub.www.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Optional<Club> findById(long id) {
        return clubRepository.findById(id);
    }

    public void save(Club club) {
        clubRepository.save(club);
    }

    @Transactional
    public void update(long clubId, String name, long countryId) {
        clubRepository.update(clubId, name, countryId);
    }

    public void delete(long id) {
        clubRepository.deleteById(id);
    }

    public List<Player> getPlayersByClubId(long id) {
        return clubRepository.getPlayerByClubId(id);
    }
}
