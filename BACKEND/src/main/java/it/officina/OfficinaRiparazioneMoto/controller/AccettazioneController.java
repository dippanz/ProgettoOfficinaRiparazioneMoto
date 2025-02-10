/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.service.AccettazioneService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 
 */
@Controller
@RequestMapping("/accettazione")
public class AccettazioneController {

    @Autowired
    private AccettazioneService accettazioneService;

    @GetMapping("")
    public String index(Model model) {
        List<RiparazioneModuloAccettazioneDto> listaRiparazioni = accettazioneService
                .getListaRiparazioniModuloAccettazioneDto();
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "accettazione/index";
    }

    @GetMapping("/inserisci")
    public String moduloAccettazione(Model model) {
        model.addAttribute("clienteVeicoloDto", new ClienteVeicoloDto());
        return "accettazione/moduloAccettazione";
    }

    @GetMapping("/dettaglio/{id}")
    public String dettaglio(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("dettaglioRiparazione", accettazioneService.getDettaglioAccettazione(idRiparazione));
        return "accettazione/dettaglio";
    }

    @PostMapping("/process_modulo_accettazione")
    public String postModuloAccettazione(@Valid @ModelAttribute("clienteVeicoloDto") ClienteVeicoloDto request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clienteVeicoloDto", request);
            return "fragments/accettazione/moduloAccettazioneForm :: moduloAccettazioneForm";
        }

        accettazioneService.salvaAccettazione(request);
        return "redirect:/accettazione";
    }

    @PatchMapping("/process_update_status_riparazione")
    public String aggiornaStatoRiparazione(@RequestParam("id") UUID idRiparazione) {
        accettazioneService.accettaRiparazione(idRiparazione);
        return "redirect:/accettazione"; //MODIFICA QUESTO METODO ED USARE API
    }

}
