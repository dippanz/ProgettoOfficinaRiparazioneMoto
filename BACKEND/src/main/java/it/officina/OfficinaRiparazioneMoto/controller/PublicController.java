package it.officina.OfficinaRiparazioneMoto.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;

@Controller
public class PublicController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/public/home";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UtenteDto utente) {
        utenteService.registraUtente(utente);
        return "redirect:/public/home";
    }
}
