/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.officina.OfficinaRiparazioneMoto.dao.ClienteDao;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.service.AccettazioneService;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 */
@Controller
@RequestMapping("/accettazione")
public class AccettazioneController {

    @Autowired
    private AccettazioneService accettazioneService;

    @GetMapping("")
    public String index() {
        return "accettazione/index";
    }

    @GetMapping("/inserisci")
    public String moduloAccettazione(Model model) {
        model.addAttribute("clienteVeicoloDto", new ClienteVeicoloDto());
        return "accettazione/moduloAccettazione";
    }

    @GetMapping("/dettaglio/{id}")
    public String dettaglio(@Param("id") String idRiparazione, Model model) {
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

}
