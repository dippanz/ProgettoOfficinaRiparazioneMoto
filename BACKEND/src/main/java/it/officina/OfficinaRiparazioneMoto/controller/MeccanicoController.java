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

/**
 * Controller for handling mechanic operations.
 * <p>
 * This controller provides endpoints to manage repairs from a mechanic's
 * perspective.
 * It includes functionality to display repairs in various states, view repair
 * details,
 * add work items (lavorazioni) to a repair, take charge of repairs, and view
 * repair history.
 * </p>
 */
@Controller
@RequestMapping("/meccanico")
public class MeccanicoController {

    @Autowired
    private MeccanicoService meccanicoService;

    /**
     * Displays the mechanic's home page with repairs currently in progress.
     * <p>
     * Retrieves a list of repairs with status
     * {@link EnumStatoRiparazione#IN_LAVORAZIONE}
     * and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the mechanic's index page
     */
    @GetMapping("")
    public String index(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService
                .getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.IN_LAVORAZIONE);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/index";
    }

    /**
     * Displays the page for taking charge of repairs.
     * <p>
     * Retrieves a list of repairs with status
     * {@link EnumStatoRiparazione#ACCETTATO}
     * and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the "take charge" page
     */
    @GetMapping("/prendi_in_carico")
    public String prendiInCarico(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService
                .getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.ACCETTATO);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/prendiInCarico";
    }

    /**
     * Displays the details of a specific repair.
     * <p>
     * This method is mapped to multiple URL patterns to support viewing details
     * from different contexts,
     * such as the general view, the "take charge" view, and the history view.
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair
     * @param model         the model used to pass data to the view
     * @return the view name for the repair detail page
     */
    @GetMapping({ "/dettaglio/{id}", "/prendi_in_carico/dettaglio/{id}", "/storico/dettaglio/{id}" })
    public String dettaglio(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("dettaglioRiparazione", meccanicoService.getDettaglioRiparazione(idRiparazione));
        return "meccanico/dettaglio";
    }

    /**
     * Displays the form for adding a work item (lavorazione) to a specific repair.
     * <p>
     * Retrieves the current list of work items for the repair and initializes a new
     * {@link AggiungiLavorazioneDto} for the form.
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair
     * @param model         the model used to pass data to the view
     * @return the view name for the "add work item" page
     */
    @GetMapping("/dettaglio/{id}/aggiungi_lavorazione")
    public String aggiungiLavorazione(@PathVariable("id") UUID idRiparazione, Model model) {
        model.addAttribute("listaLavorazioni", meccanicoService.getListaLavorazioni(idRiparazione));
        model.addAttribute("aggiungiLavorazioneDto", new AggiungiLavorazioneDto(idRiparazione));
        return "meccanico/aggiungiLavorazione";
    }

    /**
     * Processes the submission for taking charge of selected repairs.
     * <p>
     * Validates that the list of repair IDs is not empty; if it is empty, a
     * {@link BadRequestException} is thrown. Otherwise, the service is invoked to
     * process the selected repairs and the user is redirected to the mechanic's
     * home page.
     * </p>
     *
     * @param request a list of repair IDs representing the repairs to take charge
     *                of
     * @return a redirect string to the mechanic's home page
     * @throws BadRequestException if the list of repair IDs is empty
     */
    @PostMapping("/salva-prese-in-carico")
    public String postModuloAccettazione(@RequestParam("presaInCarico") List<UUID> request) throws BadRequestException {
        if (request.isEmpty()) {
            // se non arriva nessun id, lancia un'eccezione
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
        }

        meccanicoService.prendiInCaricoRiparazioni(request);
        return "redirect:/meccanico";
    }

    /**
     * Processes the form submission for adding a work item (lavorazione) to a
     * repair.
     * <p>
     * Validates the input data; if validation errors occur, the form fragment is
     * returned with error messages.
     * Otherwise, the new work item is added to the repair and the updated list of
     * work items is returned.
     * </p>
     *
     * @param request       the data for the new work item encapsulated in an
     *                      {@link AggiungiLavorazioneDto}
     * @param bindingResult holds the result of the validation and binding
     * @param model         the model used to pass data to the view
     * @return the view fragment showing the updated list of work items, or the form
     *         fragment in case of errors
     */
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

    /**
     * Displays the mechanic's repair history.
     * <p>
     * Retrieves a list of repairs with status
     * {@link EnumStatoRiparazione#COMPLETATA}
     * and adds it to the model.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the repair history page
     */
    @GetMapping("/storico")
    public String storico(Model model) {
        List<RiparazioneMeccanicoDto> listaRiparazioni = meccanicoService
                .getListaRiparazioneMeccanicoDto(EnumStatoRiparazione.COMPLETATA);
        model.addAttribute("listaRiparazioni", listaRiparazioni);
        return "meccanico/storico";
    }
}
