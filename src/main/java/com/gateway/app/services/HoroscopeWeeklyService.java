package com.gateway.app.services;

import com.gateway.app.dto.HoroscopeWeeklyDto;
import com.gateway.app.mapper.IHoroscopeDailyMapper;
import com.gateway.app.mapper.IHoroscopeWeeklyMapper;
import com.gateway.app.repositories.IHoroscopeWeeklyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoroscopeWeeklyService {

    private IHoroscopeWeeklyRepository horoscopeWeeklyRepository;
    private final SignService signService;

    public HoroscopeWeeklyService(
            IHoroscopeWeeklyRepository horoscopeWeeklyRepository,
            SignService signService) {

        this.horoscopeWeeklyRepository = horoscopeWeeklyRepository;
        this.signService = signService;


    }

    public void saveHoroscope(HoroscopeWeeklyDto horoscopeWeeklyDto) {
        horoscopeWeeklyRepository.save(IHoroscopeWeeklyMapper.INSTANCE.dtoToModel(horoscopeWeeklyDto));
    }

    public List<HoroscopeWeeklyDto> findLatestHoroscopeForAllSigns() {
        return signService.findAllSigns().stream().map(sign -> {
            return IHoroscopeWeeklyMapper.INSTANCE.modelToDto(horoscopeWeeklyRepository.findLatestWeeklyHoroscopeBySign(sign.getName())).withSignStartAndEndDate(sign.getStartDate(), sign.getEndDate());
        }).filter(l->l!=null).collect(Collectors.toList());


    }

    public void deleteAll() {
        horoscopeWeeklyRepository.deleteAll();
    }


}
