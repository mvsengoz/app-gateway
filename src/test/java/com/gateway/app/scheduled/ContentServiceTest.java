package com.gateway.app.scheduled;

import com.gateway.app.dto.HoroscopeDailyDto;
import com.gateway.app.mapper.IHoroscopeDailyMapper;
import com.gateway.app.model.HoroscopeDaily;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.*;

class ContentServiceTest implements Cloneable {

    @Autowired
    IHoroscopeDailyMapper horoscopeDailyMapper;
    @Test
    void acquireContent() {

        HoroscopeDaily model = new HoroscopeDaily("leo","test","provider", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        HoroscopeDailyDto dto = IHoroscopeDailyMapper.INSTANCE.modelToDto(model);
        System.out.println(dto);


    }


}