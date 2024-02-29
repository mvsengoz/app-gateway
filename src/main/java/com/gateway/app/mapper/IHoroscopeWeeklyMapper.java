package com.gateway.app.mapper;

import com.gateway.app.dto.HoroscopeWeeklyDto;
import com.gateway.app.model.HoroscopeWeekly;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IHoroscopeWeeklyMapper {

    IHoroscopeWeeklyMapper INSTANCE = Mappers.getMapper( IHoroscopeWeeklyMapper.class );
    HoroscopeWeekly dtoToModel(HoroscopeWeeklyDto dto);
    HoroscopeWeeklyDto modelToDto(HoroscopeWeekly model);
}
