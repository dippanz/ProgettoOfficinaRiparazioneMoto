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

/**
 * Controller for managing the admin dashboard and administrative operations.
 * <p>
 * This controller provides endpoints for user registration and modification,
 * as well as listing and modifying motorcycles and clients.
 * </p>
 */
@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private MotoService motoService;
    @Autowired
    private ClienteService clienteService;

    /**
     * Displays the admin dashboard page.
     *
     * @param model the model used to pass data to the view
     * @return the view name for the admin dashboard
     */
    @GetMapping("")
    public String getDashboard(Model model) {
        return "admin/dashboard";
    }

    /**
     * Displays the user registration form.
     *
     * @param model the model used to pass data to the view
     * @return the view name for the user registration page
     */
    @GetMapping("/registra_utente")
    public String getRegistraUtente(Model model) {
        model.addAttribute("registrazioneUtenteDto", new RegistrazioneUtenteDto());
        model.addAttribute("listaRuoli", utenteService.getRuoliUtente());
        return "admin/registrazione_utente";
    }

    /**
     * Processes the user registration form submission.
     * <p>
     * If validation errors are present, returns the registration form fragment
     * with error messages. Otherwise, registers the new user and redirects to the
     * public home page.
     * </p>
     *
     * @param utente        the user registration data
     * @param bindingResult holds the result of the validation and binding
     * @param model         the model used to pass data to the view
     * @return the view name for the form fragment in case of errors or a redirect
     *         to the home page on success
     */
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

    /**
     * Displays the list of users.
     *
     * @param model the model used to pass data to the view
     * @return the view name for the users list page
     */
    @GetMapping("utenti")
    public String getListaUtenti(Model model) {
        List<UtenteDto> utenti = utenteService.getListaUtenteDto();
        model.addAttribute("listaUtenti", utenti);
        return "admin/listaUtenti";
    }

    /**
     * Displays the user modification form for a specific user.
     *
     * @param idUtente the unique identifier of the user to modify
     * @param model    the model used to pass data to the view
     * @return the view name for the user modification page
     */
    @GetMapping("utenti/modifica/{id}")
    public String getModificaUtente(@PathVariable("id") UUID idUtente, Model model) {
        model.addAttribute("modificaUtenteDto", new ModificaUtenteDto(idUtente));
        return "admin/modificaUtente";
    }

    /**
     * Processes the user modification form submission.
     * <p>
     * If validation errors occur, returns the user modification form fragment with
     * error messages.
     * Otherwise, updates the user information and redirects to the users list page.
     * </p>
     *
     * @param request       the user modification data
     * @param bindingResult holds the result of the validation and binding
     * @param model         the model used to pass data to the view
     * @return the form fragment view in case of errors or a redirect to the users
     *         list on success
     */
    @PostMapping("process_modifica_utente")
    public String postModificaUtente(@Valid @ModelAttribute("modificaUtenteDto") ModificaUtenteDto request,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("modificaUtenteDto", request);
            return "fragments/admin/formModifica :: modificaUtenteForm";
        }

        utenteService.modificaUtente(request);
        return "redirect:utenti";
    }

    /**
     * Displays the list of motorcycles.
     *
     * @param model the model used to pass data to the view
     * @return the view name for the motorcycles list page
     */
    @GetMapping("moto")
    public String getListaMoto(Model model) {
        List<MotoClienteDto> moto = motoService.getListMotoClienteDto();
        model.addAttribute("listaMoto", moto);
        return "admin/listaMoto";
    }

    /**
     * Displays the motorcycle modification form for a specific motorcycle.
     *
     * @param idMoto the unique identifier of the motorcycle to modify
     * @param model  the model used to pass data to the view
     * @return the view name for the motorcycle modification page
     */
    @GetMapping("moto/modifica/{id}")
    public String getModificaMoto(@PathVariable("id") UUID idMoto, Model model) {
        model.addAttribute("modificaMotoDto", new ModificaMotoDto(idMoto));
        return "admin/modificaMoto";
    }

    /**
     * Processes the motorcycle modification form submission.
     * <p>
     * If validation errors are present, returns the motorcycle modification form
     * fragment
     * with error messages. Otherwise, updates the motorcycle information and
     * redirects to the motorcycles list page.
     * </p>
     *
     * @param request       the motorcycle modification data
     * @param bindingResult holds the result of the validation and binding
     * @param model         the model used to pass data to the view
     * @return the form fragment view in case of errors or a redirect to the
     *         motorcycles list on success
     */
    @PostMapping("process_modifica_moto")
    public String postModificaMoto(@Valid @ModelAttribute("modificaMotoDto") ModificaMotoDto request,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("modificaMotoDto", request);
            return "fragments/admin/formModifica :: modificaMotoForm";
        }

        motoService.modificaMoto(request);
        return "redirect:moto";
    }

    /**
     * Displays the list of clients.
     *
     * @param model the model used to pass data to the view
     * @return the view name for the clients list page
     */
    @GetMapping("clienti")
    public String getListaClienti(Model model) {
        List<ClienteDto> clienti = clienteService.getAllClienti();
        model.addAttribute("listaClienti", clienti);
        return "admin/listaClienti";
    }

    /**
     * Displays the client modification form for a specific client.
     *
     * @param idCliente the unique identifier of the client to modify
     * @param model     the model used to pass data to the view
     * @return the view name for the client modification page
     */
    @GetMapping("clienti/modifica/{id}")
    public String getModificaCliente(@PathVariable("id") UUID idCliente, Model model) {
        model.addAttribute("modificaClienteDto", new ModificaClienteDto(idCliente));
        return "admin/modificaCliente";
    }

    /**
     * Processes the client modification form submission.
     * <p>
     * If validation errors occur, returns the client modification form fragment
     * with error messages. Otherwise, updates the client information and redirects
     * to the clients list page.
     * </p>
     *
     * @param request       the client modification data
     * @param bindingResult holds the result of the validation and binding
     * @param model         the model used to pass data to the view
     * @return the form fragment view in case of errors or a redirect to the clients
     *         list on success
     */
    @PostMapping("process_modifica_cliente")
    public String postModificaCliente(@Valid @ModelAttribute("modificaClienteDto") ModificaClienteDto request,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("modificaClienteDto", request);
            return "fragments/admin/formModifica :: modificaClienteForm";
        }

        clienteService.modificaCliente(request);
        return "redirect:clienti";
    }
}
