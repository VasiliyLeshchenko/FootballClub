package com.footballclub.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatisticsDTO {
    private Long id;
    private PlayerDTO player;
    private ClubDTO club;
    private Integer goals;
}
