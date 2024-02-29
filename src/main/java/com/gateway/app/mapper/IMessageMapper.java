package com.gateway.app.mapper;

import com.gateway.app.dto.MessageDto;
import com.gateway.app.dto.SignDto;
import com.gateway.app.model.Message;
import com.gateway.app.model.Sign;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMessageMapper {
    IMessageMapper INSTANCE = Mappers.getMapper( IMessageMapper.class );
    Message dtoToModel(MessageDto dto);
    MessageDto modelToDto(Message model);
}
