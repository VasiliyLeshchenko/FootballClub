package com.footballclub.core.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private PositionDTO position;
    private ClubDTO club;
    private CountryDTO citizenship;
}
