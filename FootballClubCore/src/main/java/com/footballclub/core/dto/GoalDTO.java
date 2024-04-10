package com.footballclub.core.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private GameDTO game;
    private PlayerDTO player;
    private ClubDTO club;
    private Boolean homeClub;
    private Integer numGoals;
}
