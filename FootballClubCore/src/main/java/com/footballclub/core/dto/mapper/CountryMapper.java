package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.CountryDTO;
import com.footballclub.core.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    Country toCountry(CountryDTO countryDTO);
    CountryDTO fromCountry(Country country);
}
