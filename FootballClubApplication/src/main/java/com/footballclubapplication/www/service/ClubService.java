package com.footballclubapplication.www.service;

import com.footballclub.core.entity.Club;
import com.footballclub.core.entity.Player;
import com.footballclubapplication.www.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Optional<Club> findById(long id) {
        return clubRepository.findById(id);
    }

  /*  //public List<Club> findByName(String name) {
       return clubRepository.findByName(name);
    }*/

    public void save(Club club) {
        clubRepository.save(club);
    }

    @Transactional
    public void update(Club club) {
        clubRepository.save(club);
    }

    public void delete(long id) {
        clubRepository.deleteById(id);
    }

    public List<Player> getPlayersByClubId(long id) {
        return clubRepository.getPlayerByClubId(id);
    }
}
