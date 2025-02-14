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

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private MotoService motoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public String getDashboard(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("/registra_utente")
    public String getRegistraUtente(Model model) {
        model.addAttribute("registrazioneUtenteDto", new RegistrazioneUtenteDto());
        model.addAttribute("listaRuoli", utenteService.getRuoliUtente());
        return "admin/registrazione_utente";
    }

    @PostMapping("/process_registra_utente")
    public String postRegistraUtente(@Valid @ModelAttribute("registrazioneUtenteDto") RegistrazioneUtenteDto utente,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("registrazioneUtenteDto", utente);
            model.addAttribute("listaRuoli", utenteService.getRuoliUtente());
            return "fragments/admin/registrazioneForm :: registrazioneForm";
        }

        utenteService.registraUtente(utente);
        return "redirect:/public/home";
    }

    @GetMapping("utenti")
    public String getListaUtenti(Model model) {
        List<UtenteDto> utenti = utenteService.getListaUtenteDto();
        model.addAttribute("listaUtenti", utenti);
        return "admin/listaUtenti";
    }

    @GetMapping("utenti/modifica/{id}")
    public String getModificaUtente(@PathVariable("id") UUID idUtente, Model model) {
        model.addAttribute("modificaUtenteDto", new ModificaUtenteDto(idUtente));
        return "admin/modificaUtente";
    }

    @PostMapping("process_modifica_utente")
    public String postModificaUtente(@Valid @ModelAttribute("modificaUtenteDto") ModificaUtenteDto request, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("modificaUtenteDto",request);
            return "fragments/admin/formModifica :: modificaUtenteForm";
        }

        utenteService.modificaUtente(request);
        return "redirect:utenti";
    }

    @GetMapping("moto")
    public String getListaMoto(Model model) {
        List<MotoClienteDto> moto = motoService.getListMotoClienteDto();
        model.addAttribute("listaMoto", moto);
        return "admin/listaMoto";
    }

    @GetMapping("moto/modifica/{id}")
    public String getModificaMoto(@PathVariable("id") UUID idMoto, Model model) {
        model.addAttribute("modificaMotoDto", new ModificaMotoDto(idMoto));
        return "admin/modificaMoto";
    }

    @PostMapping("process_modifica_moto")
    public String postModificaMoto(@Valid @ModelAttribute("modificaMotoDto") ModificaMotoDto request, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("modificaMotoDto",request);
            return "fragments/admin/formModifica :: modificaMotoForm";
        }

        motoService.modificaMoto(request);
        return "redirect:moto";
    }

    @GetMapping("clienti")
    public String getListaClienti(Model model) {
        List<ClienteDto> clienti = clienteService.getAllClienti();
        model.addAttribute("listaClienti", clienti);
        return "admin/listaClienti";
    }

    @GetMapping("clienti/modifica/{id}")
    public String getModificaCliente(@PathVariable("id") UUID idCliente, Model model) {
        model.addAttribute("modificaClienteDto", new ModificaClienteDto(idCliente));
        return "admin/modificaCliente";
    }

    @PostMapping("process_modifica_cliente")
    public String postModificaCliente(@Valid @ModelAttribute("modificaClienteDto") ModificaClienteDto request, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("modificaClienteDto",request);
            return "fragments/admin/formModifica :: modificaClienteForm";
        }

        clienteService.modificaCliente(request);
        return "redirect:clienti";
    }
}
