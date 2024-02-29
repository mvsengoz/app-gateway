package com.gateway.app.mapper;

import com.gateway.app.dto.HoroscopeMonthlyDto;
import com.gateway.app.model.HoroscopeMonthly;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IHoroscopeMonthlyMapper {

    IHoroscopeMonthlyMapper INSTANCE = Mappers.getMapper( IHoroscopeMonthlyMapper.class );
    HoroscopeMonthly dtoToModel(HoroscopeMonthlyDto dto);
    HoroscopeMonthlyDto modelToDto(HoroscopeMonthly model);
}
