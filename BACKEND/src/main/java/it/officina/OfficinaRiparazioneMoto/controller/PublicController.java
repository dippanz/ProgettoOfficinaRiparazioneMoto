package it.officina.OfficinaRiparazioneMoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import it.officina.OfficinaRiparazioneMoto.dto.publics.CercaRiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.service.PublicService;
import jakarta.validation.Valid;

@Controller
public class PublicController {

    @Autowired
    private PublicService publicService;

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
    public String getRiparazione(@Valid @ModelAttribute("cercaRiparazioneDto") CercaRiparazioneDto request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // errori di validazione
            model.addAttribute("cercaRiparazioneDto", request);
            return "fragments/publics/ricercaRiparazione :: cercaRiparazioneForm";
        }

        // carico i dettagli della riparazione
        model.addAttribute("riparazioneDatiGenerali",
                publicService.getRiparazioneDettaglioGenerale(request.getCodiceServizio(),
                request.getTarga()));

        return "fragments/publics/ricercaRiparazione :: riparazioneDettagliGenerali";
    }

}
