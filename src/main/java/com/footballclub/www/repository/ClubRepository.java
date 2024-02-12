package com.footballclub.www.repository;

import com.footballclub.www.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClubRepository extends JpaRepository<Club, Long> {

    @Modifying
    @Query("UPDATE Club SET name= :clubName, country.id= :countryId WHERE id= :clubId")
    void update(long clubId, String clubName, long countryId);
}
