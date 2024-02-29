package com.gateway.app.controllers;


import com.gateway.app.dto.MessageDto;
import com.gateway.app.services.HoroscopeDailyService;
import com.gateway.app.services.HoroscopeMonthlyService;
import com.gateway.app.services.HoroscopeWeeklyService;
import com.gateway.app.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/gateway_h")
public class HoroscopeController {


    private HoroscopeDailyService horoscopeDailyService;

    private HoroscopeWeeklyService horoscopeWeeklyService;

    private HoroscopeMonthlyService horoscopeMonthlyService;

    private MessageService messageService;


    public HoroscopeController(HoroscopeDailyService horoscopeDailyService, HoroscopeWeeklyService horoscopeWeeklyService, HoroscopeMonthlyService horoscopeMonthlyService, MessageService messageService) {
        this.horoscopeDailyService = horoscopeDailyService;
        this.horoscopeWeeklyService = horoscopeWeeklyService;
        this.horoscopeMonthlyService = horoscopeMonthlyService;
        this.messageService = messageService;
    }

    @GetMapping(path = "daily-horoscopes")
    public ResponseEntity<?> getDailyHoroscopes() {
        return ResponseEntity.ok(horoscopeDailyService.findLatestHoroscopeForAllSigns());
    }

    @GetMapping(path = "weekly-horoscopes")
    public ResponseEntity<?> getWeeklyHoroscopes() {
        return ResponseEntity.ok(horoscopeWeeklyService.findLatestHoroscopeForAllSigns());
    }

    @GetMapping(path = "monthly-horoscopes")
    public ResponseEntity<?> getMonthlyHoroscopes() {
        return ResponseEntity.ok(horoscopeMonthlyService.findLatestHoroscopeForAllSigns());
    }

    @PostMapping(path = "message")
    public ResponseEntity<?> saveMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.saveMessage(messageDto));
    }

}