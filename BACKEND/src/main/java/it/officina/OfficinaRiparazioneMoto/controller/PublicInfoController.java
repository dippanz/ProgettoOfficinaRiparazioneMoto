package it.officina.OfficinaRiparazioneMoto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicInfoController {

    @GetMapping("/")
    public String home() {
        return "Benvenuto nell'officina di riparazione moto!";
    }
}
