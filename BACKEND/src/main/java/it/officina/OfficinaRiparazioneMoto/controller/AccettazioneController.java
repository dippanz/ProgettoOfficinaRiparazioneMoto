/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioniModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
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
    private MotoMapper motoMapper;

    @Autowired
    private ClienteMapper clienteMapper;
    
    @Autowired
    private RiparazioneMapper riparazioneMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MotoService motoService;

    @Autowired
    private RiparazioneService riparazioneService;

    @GetMapping("")
    public String index() {
        return "accettazione/index";
    }

    @GetMapping("/inserisci")
    public String moduloAccettazione(Model model) {
        model.addAttribute("clienteVeicoloDto", new ClienteVeicoloDto());
        return "accettazione/moduloAccettazione";
    }

    @GetMapping("/index4")
    public String index4() {
        return "accettazione/dettaglio";
    }

    @PostMapping("/process_modulo_accettazione")
    public String postModuloAccettazione(@Valid @ModelAttribute("clienteVeicoloDto") ClienteVeicoloDto request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clienteVeicoloDto", request);
            return "fragments/accettazione/moduloAccettazioneForm :: moduloAccettazioneForm";
        }

        // CASO IN CUI CLIENTE NON ESISTA

        // salvo il cliente (dovrebbe essere opzionale se gia presente)
        ClienteDto cliente = clienteService.salvaCliente(clienteMapper.mapToEntity(request));

        // salvo il veicolo (dovrebbe essere opzionale se gia presente)
        MotoDto moto = motoMapper.mapToEntity(request);
        moto.setIdCliente(cliente.getId());
        moto = motoService.salvaMoto(moto);

        // salvo la riparazione come accettata
        RiparazioneDto riparazione = riparazioneMapper.mapToEntity(request);
        riparazione.setIdMoto(moto.getId());
        riparazione = riparazioneService.salvaRiparazioneAccettata(riparazione);

        // CASO IN CUI CLIENTE ESISTA E MOTO NO

        // CASO IN CUI CLIENTE E MOTO ESISTONO

        // PASSO OGGETTI DIRETTAMENTE AL REDIRECT

        // quando torno alla pagina precedente inserisco i dati che ho caricato nel db
        RiparazioniModuloAccettazioneDto riparazioneAttribute = new RiparazioniModuloAccettazioneDto(
                riparazione.getCodiceServizio(), moto.getTarga(), cliente.getEmail(),
                riparazione.getStatoRiparazione());
        redirectAttributes.addAttribute("riparazioni", riparazioneAttribute);

        return "redirect:/accettazione";
    }

}
