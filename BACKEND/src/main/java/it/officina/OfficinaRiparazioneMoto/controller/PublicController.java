package it.officina.OfficinaRiparazioneMoto.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.publics.CercaRiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Controller
public class PublicController {

    @Autowired
    private RiparazioneService riparazioneService;

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
    public String getCercaRiparazione(Model model) {
        model.addAttribute("cercaRiparazioneDto", new CercaRiparazioneDto());
        return "publics/cerca_riparazione";
    }

    @GetMapping("/public/process_cerca_riparazione")
    public String getRiparazione(@Valid @ModelAttribute("cercaRiparazioneDto") CercaRiparazioneDto cercaRiparazioneDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // errori di validazione
            model.addAttribute("cercaRiparazioneDto", cercaRiparazioneDto);
            return "fragments/publics/ricercaRiparazione :: cercaRiparazioneForm";
        }

        // carico i dettagli della riparazione
        model.addAttribute("riparazioneDatiGenerali",
                riparazioneService.getRiparazioneDettaglioGenerale(cercaRiparazioneDto.getCodiceServizio(),
                        cercaRiparazioneDto.getTarga()));

        return "fragments/publics/ricercaRiparazione :: riparazioneDettagliGenerali";
    }

}
