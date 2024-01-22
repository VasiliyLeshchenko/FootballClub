package com.footballclab.www.repository;

import com.footballclab.www.entity.Club;
import com.footballclab.www.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Modifying
    @Query("SELECT c FROM Club c INNER JOIN c.country co WHERE co.id = :id")
    List<Club> findClubsByCountryId(long id);
}
