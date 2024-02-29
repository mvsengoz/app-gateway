package com.gateway.app.services;

import com.gateway.app.dto.HoroscopeMonthlyDto;
import com.gateway.app.mapper.IHoroscopeMonthlyMapper;
import com.gateway.app.repositories.IHoroscopeMonthlyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoroscopeMonthlyService {
    private IHoroscopeMonthlyRepository horoscopeMonthlyRepository;
    private SignService signService;

    public HoroscopeMonthlyService(
            IHoroscopeMonthlyRepository horoscopeMonthlyRepository,
            SignService signService) {

        this.horoscopeMonthlyRepository = horoscopeMonthlyRepository;
        this.signService = signService;

    }


    public void saveHoroscope(HoroscopeMonthlyDto horoscopeMonthlyDto) {
        horoscopeMonthlyRepository.save(IHoroscopeMonthlyMapper.INSTANCE.dtoToModel(horoscopeMonthlyDto));
    }

  
    public List<HoroscopeMonthlyDto> findLatestHoroscopeForAllSigns() {
        return signService.findAllSigns().stream().map(sign -> {
            return IHoroscopeMonthlyMapper.INSTANCE.modelToDto(horoscopeMonthlyRepository.findLatestMonthlyHoroscopeBySign(sign.getName())).withSignStartAndEndDate(sign.getStartDate(), sign.getEndDate());
        }).filter(l->l!=null).collect(Collectors.toList());
    }

    public void deleteAll() {
        horoscopeMonthlyRepository.deleteAll();
    }
}
