package com.gateway.app.services;

import com.gateway.app.dto.SignDto;
import com.gateway.app.exceptions.RedirectException;
import com.gateway.app.mapper.ISignMapper;
import com.gateway.app.model.Sign;
import com.gateway.app.repositories.ISignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignService {

    private ISignRepository signRepository;

    public SignService(ISignRepository signRepository
                               ) {
        this.signRepository = signRepository;
    }
    public Sign findSignById(String id) {
        return signRepository.findById(id).orElseThrow(() -> new RedirectException("Horoscope Not Found"));
    }


    public List<SignDto> findAllSigns() {
        return signRepository.findAll().stream()
                .map(model -> ISignMapper.INSTANCE.modelToDto(model))
                .collect(Collectors.toList());
    }

    public Sign saveSign(SignDto signDto) {
        return signRepository.save(ISignMapper.INSTANCE.dtoToModel(signDto));
    }



}
