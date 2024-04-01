package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    Player toPlayer(PlayerDTO playerDTO);
    PlayerDTO fromPlayer(Player player);
}
