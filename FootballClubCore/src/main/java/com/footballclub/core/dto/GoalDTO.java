package com.footballclub.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private GameDTO game;
    private PlayerDTO player;
    private ClubDTO club;
    private Boolean homeClub;
    private Integer numGoals;
}
