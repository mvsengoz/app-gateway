package com.gateway.app.services;

import com.gateway.app.dto.MessageDto;
import com.gateway.app.dto.SignDto;
import com.gateway.app.exceptions.RedirectException;
import com.gateway.app.mapper.IMessageMapper;
import com.gateway.app.mapper.ISignMapper;
import com.gateway.app.model.Message;
import com.gateway.app.model.Sign;
import com.gateway.app.repositories.IMessageRepository;
import com.gateway.app.repositories.ISignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private IMessageRepository messageRepository;

    public MessageService(IMessageRepository messageRepository
                               ) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(MessageDto messageDto) {
        return messageRepository.save(IMessageMapper.INSTANCE.dtoToModel(messageDto));
    }



}
