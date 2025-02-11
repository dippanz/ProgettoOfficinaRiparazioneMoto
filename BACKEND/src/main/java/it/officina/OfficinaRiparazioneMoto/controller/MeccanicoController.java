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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.RiparazioneMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.service.MeccanicoService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/meccanico")
public class MeccanicoController {

    @Autowired
    private MeccanicoService meccanicoService;

    @GetMapping("")
    public String index(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService.getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.IN_LAVORAZIONE);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/index";
    }

    @GetMapping("/prendi_in_carico")
    public String prendiInCarico(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService.getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.ACCETTATO);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/prendiInCarico";
    }

    @GetMapping({"/dettaglio/{id}", "/prendi_in_carico/dettaglio/{id}", "/storico/dettaglio/{id}"})
    public String dettaglio(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("dettaglioRiparazione", meccanicoService.getDettaglioRiparazione(idRiparazione));
        return "meccanico/dettaglio";
    }

    @GetMapping("/dettaglio/{id}/aggiungi_lavorazione")
    public String aggiungiLavorazione(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("listaLavorazioni", meccanicoService.getListaLavorazioni(idRiparazione));
        model.addAttribute("aggiungiLavorazioneDto", new AggiungiLavorazioneDto(idRiparazione));
        return "meccanico/aggiungiLavorazione";
    }

    @PostMapping("/salva-prese-in-carico")
    public String postModuloAccettazione(@RequestParam("presaInCarico") List<UUID> request) throws BadRequestException {
        if (request.isEmpty()) {
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
        }

        meccanicoService.prendiInCaricoRiparazioni(request);
        return "redirect:/meccanico";
    }

    @PostMapping("/process_aggiungi_lavorazione")
    public String postAggiungiLavorazione(
            @Valid @ModelAttribute("aggiungiLavorazioneDto") AggiungiLavorazioneDto request,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("aggiungiLavorazioneDto", request);
            return "fragments/meccanico/aggiungiLavorazione :: aggiungiLavorazioneForm";
        }

        meccanicoService.aggiungiLavorazione(request);

        List<RiparazioneLavorazioneDto> listaLavorazioni = meccanicoService
                .getListaLavorazioni(request.getIdRiparazione());

        model.addAttribute("listaLavorazioni", listaLavorazioni);
        return "fragments/meccanico/aggiungiLavorazione :: listaLavorazioni";
    }

    @GetMapping("/storico")
    public String storico(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService.getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.COMPLETATA);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/storico";
    }
}
