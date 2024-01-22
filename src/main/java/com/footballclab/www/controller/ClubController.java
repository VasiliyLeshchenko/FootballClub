package com.footballclab.www.controller;

import com.footballclab.www.entity.Club;
import com.footballclab.www.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public Club findById(@PathVariable("id") long id) {
        return clubService.findById(id).get();

    }
}
