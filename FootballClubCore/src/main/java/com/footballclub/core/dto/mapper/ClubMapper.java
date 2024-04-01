package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.ClubDTO;
import com.footballclub.core.entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClubMapper {
    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);


    Club toClub(ClubDTO clubDTO);

    ClubDTO fromClub(Club club);
}
