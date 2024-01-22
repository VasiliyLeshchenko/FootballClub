package com.footballclab.www.service;

import com.footballclab.www.entity.Club;
import com.footballclab.www.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
