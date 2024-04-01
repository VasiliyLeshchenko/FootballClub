package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.GameDTO;
import com.footballclub.core.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game toGame(GameDTO gameDTO);
    GameDTO fromGame(Game game);
}
