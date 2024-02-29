package com.gateway.app.mapper;

import com.gateway.app.dto.SignDto;
import com.gateway.app.model.Sign;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISignMapper {
    ISignMapper INSTANCE = Mappers.getMapper( ISignMapper.class );
    Sign dtoToModel(SignDto dto);
    SignDto modelToDto(Sign model);
}
