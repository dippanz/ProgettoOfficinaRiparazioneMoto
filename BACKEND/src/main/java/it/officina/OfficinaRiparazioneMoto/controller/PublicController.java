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

/**
 * Controller for handling public pages and operations.
 * <p>
 * This controller manages the public-facing pages including redirection to the
 * home page,
 * rendering the home and login pages, and providing functionality to search for
 * a repair.
 * It leverages the {@link PublicService} to retrieve repair details.
 * </p>
 */
@Controller
public class PublicController {

    @Autowired
    private PublicService publicService;

    /**
     * Redirects the root URL to the public home page.
     *
     * @return a redirect string to the public home page
     */
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/public/home";
    }

    /**
     * Displays the public home page.
     *
     * @return the view name for the public home page
     */
    @GetMapping("/public/home")
    public String home() {
        return "publics/home";
    }

    /**
     * Displays the public login page.
     *
     * @return the view name for the public login page
     */
    @GetMapping("/public/login")
    public String login() {
        return "publics/login";
    }

    /**
     * Displays the repair search form.
     * <p>
     * Initializes a new {@link CercaRiparazioneDto} and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the repair search page
     */
    @GetMapping("/public/cerca_riparazione")
    public String getCercaRiparazione(Model model) {
        model.addAttribute("cercaRiparazioneDto", new CercaRiparazioneDto());
        return "publics/cerca_riparazione";
    }

    /**
     * Processes the repair search request.
     * <p>
     * Validates the input data and, in case of validation errors, returns the
     * search form fragment
     * with error messages. If validation is successful, it retrieves the general
     * details of the repair
     * based on the service code and vehicle plate provided, and returns the
     * corresponding details fragment.
     * </p>
     *
     * @param request       the {@link CercaRiparazioneDto} containing the search
     *                      criteria
     * @param bindingResult holds the result of validation and binding
     * @param model         the model used to pass data to the view
     * @return the view fragment containing either the search form with errors or
     *         the repair details
     */
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
