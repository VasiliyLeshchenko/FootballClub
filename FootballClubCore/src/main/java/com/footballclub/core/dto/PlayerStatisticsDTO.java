package com.footballclub.core.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatisticsDTO {
    private Long id;
    private PlayerDTO player;
    private ClubDTO club;
    private Integer goals;
}
