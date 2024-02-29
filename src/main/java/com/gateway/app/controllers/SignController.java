package com.gateway.app.controllers;


import com.gateway.app.dto.SignDto;
import com.gateway.app.services.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://localhost:8080/devicemine/handsetinfo
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/gateway_s")
public class SignController {

    private SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @GetMapping(path = "sign/{id}")
    public ResponseEntity<?> getSign(@PathVariable String id){
        return ResponseEntity.ok(signService.findSignById(id));
    }

    @GetMapping(path = "signs")
    public ResponseEntity<?> getAllSigns(){
        return ResponseEntity.ok(signService.findAllSigns());
    }

    @PostMapping(path = "sign")
    public ResponseEntity<?> addSign(@RequestBody SignDto signDto){
        return ResponseEntity.ok(signService.saveSign(signDto));
    }

}
