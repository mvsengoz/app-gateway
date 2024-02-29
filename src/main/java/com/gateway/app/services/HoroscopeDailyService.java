package com.gateway.app.services;

import com.gateway.app.dto.HoroscopeDailyDto;
import com.gateway.app.mapper.IHoroscopeDailyMapper;
import com.gateway.app.repositories.IHoroscopeDailyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoroscopeDailyService {

    private final IHoroscopeDailyRepository horoscopeDailyRepository;

    private final SignService signService;

    public HoroscopeDailyService(
            IHoroscopeDailyRepository horoscopeDailyRepository,
            SignService signService) {

        this.signService = signService;
        this.horoscopeDailyRepository = horoscopeDailyRepository;
    }

    public void saveHoroscope(HoroscopeDailyDto horoscopeDailyDto) {
        horoscopeDailyRepository.save(IHoroscopeDailyMapper.INSTANCE.dtoToModel(horoscopeDailyDto));
    }

    public List<HoroscopeDailyDto> findLatestHoroscopeForAllSigns() {
        return signService.findAllSigns().stream().map(sign -> IHoroscopeDailyMapper.INSTANCE.modelToDto(horoscopeDailyRepository.findLatestDailyHoroscopeBySign(sign.getName())).withSignStartAndEndDate(sign.getStartDate(), sign.getEndDate())).filter(l -> l != null).collect(Collectors.toList());
    }

    public void deleteAll() {
        horoscopeDailyRepository.deleteAll();
    }


}
