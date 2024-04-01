package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.PlayerDTO;
import com.footballclub.core.dto.PlayerStatisticsDTO;
import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.PlayerStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerStatisticsMapper {
    PlayerStatisticsMapper INSTANCE = Mappers.getMapper(PlayerStatisticsMapper.class);

    PlayerStatistics toPlayerStatistics(PlayerStatisticsDTO playerStatisticsDTO);
    PlayerStatisticsDTO fromPlayerStatistics(PlayerStatistics playerStatistics);
}
