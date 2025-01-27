package it.officina.OfficinaRiparazioneMoto.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.officina.OfficinaRiparazioneMoto.dto.CercaRiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;

@Controller
public class PublicController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/public/home";
    }

    @GetMapping("/public/home")
    public String home() {
        return "publics/home";
    }

    @GetMapping("/public/login")
    public String login() {
        return "publics/login";
    }

    @GetMapping("/public/cerca_riparazione")
    public String getCercaRiparazione() {
        return "publics/cerca_riparazione";
    }

    @PostMapping("/public/process_cerca_riparazione")
    public String postMethodName(@ModelAttribute CercaRiparazioneDto cercaRiparazioneDto) {
       
        

        
        return "";
    }
    
}
