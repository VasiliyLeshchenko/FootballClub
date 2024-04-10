package com.footballclub.core.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private Long id;
    private String name;
    private CountryDTO country;
}
