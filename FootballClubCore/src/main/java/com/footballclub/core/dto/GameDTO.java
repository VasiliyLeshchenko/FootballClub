package com.footballclub.core.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private ClubDTO homeClub;
    private ClubDTO awayClub;
    private Long homeClubScore;
    private Long awayClubScore;
    private Date date;
}
