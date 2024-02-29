package com.gateway.app.mapper;

import com.gateway.app.dto.HoroscopeDailyDto;
import com.gateway.app.model.HoroscopeDaily;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IHoroscopeDailyMapper {

    IHoroscopeDailyMapper INSTANCE = Mappers.getMapper( IHoroscopeDailyMapper.class );
    HoroscopeDaily dtoToModel(HoroscopeDailyDto dto);
    HoroscopeDailyDto modelToDto(HoroscopeDaily model);
}
