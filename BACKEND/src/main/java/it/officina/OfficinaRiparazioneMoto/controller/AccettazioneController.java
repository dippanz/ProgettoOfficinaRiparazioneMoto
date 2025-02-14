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
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.service.AccettazioneService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for managing the acceptance process.
 * <p>
 * This controller handles web requests related to the acceptance process,
 * including displaying the acceptance module, listing repair modules,
 * showing details of a specific acceptance, and processing acceptance forms.
 * </p>
 */
@Controller
@RequestMapping("/accettazione")
public class AccettazioneController {

    @Autowired
    private AccettazioneService accettazioneService;

    /**
     * Displays the main acceptance page.
     * <p>
     * Retrieves a list of repair modules for acceptance and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the acceptance index page
     */
    @GetMapping("")
    public String index(Model model) {
        List<RiparazioneModuloAccettazioneDto> listaRiparazioni = accettazioneService
                .getListaRiparazioniModuloAccettazioneDto();
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "accettazione/index";
    }

    /**
     * Displays the acceptance module form.
     * <p>
     * Adds a new {@link ClienteVeicoloDto} instance to the model to capture
     * customer and vehicle data.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the acceptance module form
     */
    @GetMapping("/inserisci")
    public String moduloAccettazione(Model model) {
        model.addAttribute("clienteVeicoloDto", new ClienteVeicoloDto());
        return "accettazione/moduloAccettazione";
    }

    /**
     * Displays the historical record of accepted repairs.
     * <p>
     * Retrieves a list of accepted repairs and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the acceptance history page
     */
    @GetMapping("/storico")
    public String storico(Model model) {
        List<RiparazioneAccettazioneDto> listaRiparazioni = accettazioneService.getListaRiparazioneAccettazioneDto();
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "accettazione/storico";
    }

    /**
     * Displays the details of a specific repair acceptance.
     * <p>
     * This method is mapped to two URL patterns to accommodate both current and
     * historical details.
     * It retrieves the acceptance details based on the provided repair identifier.
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair acceptance
     * @param model         the model used to pass data to the view
     * @return the view name for the acceptance detail page
     */
    @GetMapping({ "/dettaglio/{id}", "/storico/dettaglio/{id}" })
    public String dettaglio(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("dettaglioRiparazione", accettazioneService.getDettaglioAccettazione(idRiparazione));
        return "accettazione/dettaglio";
    }

    /**
     * Processes the submitted acceptance form.
     * <p>
     * Validates the provided customer and vehicle data. If validation errors are
     * found,
     * returns the form fragment with error messages. Otherwise, saves the
     * acceptance and
     * redirects to the main acceptance page.
     * </p>
     *
     * @param request       the {@link ClienteVeicoloDto} containing customer and
     *                      vehicle information
     * @param bindingResult the result of the validation binding
     * @param model         the model used to pass data to the view
     * @return the form fragment view in case of errors, or a redirect string to the
     *         acceptance index page if successful
     */
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
