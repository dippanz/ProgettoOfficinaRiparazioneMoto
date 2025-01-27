package it.officina.OfficinaRiparazioneMoto.controller;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.officina.OfficinaRiparazioneMoto.dto.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UtenteService utenteService;
    
    @PostMapping("/process_registra_utente")
    public String postRegistraUtente(@Valid @ModelAttribute("registrazioneUtenteDto") RegistrazioneUtenteDto utente, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("registrazioneUtenteDto", utente);
            return "fragments/registrazioneForm :: registrazioneForm";
        }

        utenteService.registraUtente(utente);
        return "redirect:/public/home";
    }

    @GetMapping("/registra_utente")
    public String getRegistraUtente(Model model) {
        model.addAttribute("registrazioneUtenteDto", new RegistrazioneUtenteDto());
        return "admin/registrazione_utente";
    }
}
