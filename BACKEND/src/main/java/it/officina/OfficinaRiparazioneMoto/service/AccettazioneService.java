package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;

/**
 * Service interface for managing the acceptance process.
 * <p>
 * This interface defines methods to handle the acceptance of repairs,
 * including saving a new acceptance request, retrieving detailed and summary
 * data for repairs,
 * and accepting a repair.
 * </p>
 */
public interface AccettazioneService {

    /**
     * Saves a repair acceptance record.
     * <p>
     * This method processes the acceptance form data. It first retrieves the
     * vehicle information
     * if the vehicle ID is provided. If not, it either retrieves an existing client
     * or saves a new client
     * based on the provided data. Then, it creates a new vehicle record, associates
     * it with the client,
     * and saves it. Finally, it creates a new repair record (with a due date one
     * month ahead) and saves it as accepted.
     * </p>
     *
     * @param request a {@link ClienteVeicoloDto} containing client and vehicle data
     *                for the acceptance
     */
    void salvaAccettazione(ClienteVeicoloDto request);

    /**
     * Retrieves detailed acceptance information for a repair.
     * <p>
     * The method fetches repair details based on the repair ID and maps the data
     * into a
     * {@link DettaglioAccettazioneDto} which contains key information for display.
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair
     * @return a {@link DettaglioAccettazioneDto} containing detailed acceptance
     *         data
     */
    DettaglioAccettazioneDto getDettaglioAccettazione(UUID idRiparazione);

    /**
     * Retrieves a list of repair records for acceptance in module form.
     * <p>
     * The method fetches repair records that are in the "REGISTRATO" state for the
     * currently authenticated user,
     * and maps them to a list of {@link RiparazioneModuloAccettazioneDto} objects
     * containing summary information.
     * </p>
     *
     * @return a list of {@link RiparazioneModuloAccettazioneDto} objects
     *         representing repair records
     */
    List<RiparazioneModuloAccettazioneDto> getListaRiparazioniModuloAccettazioneDto();

    /**
     * Accepts a repair by updating its state.
     * <p>
     * This method advances the repair's state by calling the repair service's
     * update method,
     * without assigning any mechanic (null is passed as the mechanic).
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair to accept
     */
    void accettaRiparazione(UUID idRiparazione);

    /**
     * Retrieves a list of repair records for acceptance, including a variety of
     * states.
     * <p>
     * This method fetches repair records for the currently authenticated user that
     * are in one of the following states:
     * ACCETTATO, RIFIUTATO, IN_LAVORAZIONE, or COMPLETATA. The data is mapped to a
     * list of
     * {@link RiparazioneAccettazioneDto} objects containing summary information.
     * </p>
     *
     * @return a list of {@link RiparazioneAccettazioneDto} objects representing the
     *         repair records
     */
    List<RiparazioneAccettazioneDto> getListaRiparazioneAccettazioneDto();
}
