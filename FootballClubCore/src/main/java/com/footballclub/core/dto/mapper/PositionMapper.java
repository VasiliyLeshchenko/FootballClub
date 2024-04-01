package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.PositionDTO;
import com.footballclub.core.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionMapper {
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    Position toPosition(PositionDTO positionDTO);
    PositionDTO fromPosition(Position position);
}
