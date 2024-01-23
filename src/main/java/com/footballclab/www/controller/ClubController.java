package com.footballclab.www.controller;

import com.footballclab.www.dto.ClubUpdateDTO;
import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import com.footballclab.www.exeption.ClubNotFoundException;
import com.footballclab.www.exeption.CountryNotFoundException;
import com.footballclab.www.service.ClubService;
import com.footballclab.www.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;
    private final CountryService countryService;

    @Autowired
    public ClubController(ClubService clubService, CountryService countryService) {
        this.clubService = clubService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public Club findById(@PathVariable("id") long id) {
        return clubService.findById(id)
                .orElseThrow(() -> new ClubNotFoundException("Club not found"));
    }

    @PostMapping
    public void  saveClub(
            @RequestParam("clubName") String clubName,
            @RequestParam("countryId") long countryId
    ) {
        Country country = countryService.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));

        Club newClub = new Club(clubName);
        newClub.setCountry(country);

        clubService.save(newClub);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClub(
            @PathVariable("id") long clubId,
            @RequestBody ClubUpdateDTO clubUpdateDTO
    ) {
        clubService.update(
                clubId,
                clubUpdateDTO.getClubName(),
                clubUpdateDTO.getCountryId()
        );

        return ResponseEntity
                .ok("Club updated successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteClub(
            @PathVariable("id") long id
    ) {
        clubService.delete(id);
    }
}
