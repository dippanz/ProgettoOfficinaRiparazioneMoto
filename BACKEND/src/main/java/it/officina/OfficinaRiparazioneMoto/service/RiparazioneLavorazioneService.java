package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;

/**
 * Service interface for managing repair work items.
 * <p>
 * This interface defines methods to retrieve, add, and delete work items
 * (lavorazioni)
 * associated with a repair.
 * </p>
 */
public interface RiparazioneLavorazioneService {

    /**
     * Retrieves a list of repair work DTOs associated with a given repair.
     *
     * @param idRiparazione the unique identifier of the repair
     * @return a list of {@link RiparazioneLavorazioneDto} objects for the specified
     *         repair
     */
    List<RiparazioneLavorazioneDto> getListaRiparazioneLavorazioneDto(UUID idRiparazione);

    /**
     * Adds a new repair work entry.
     * <p>
     * The method creates a new {@link RiparazioneLavorazione} entity based on the
     * provided DTO,
     * sets its description and hours, and associates it with an existing repair.
     * The repair is
     * retrieved using the {@link RiparazioneService}.
     * </p>
     *
     * @param aggiungiLavorazioneDto the DTO containing data for the new repair work
     *                               entry
     */
    void aggiungiLavorazione(AggiungiLavorazioneDto aggiungiLavorazioneDto);

     /**
     * Deletes a repair work entry identified by its unique identifier.
     *
     * @param idLavorazione the unique identifier of the repair work entry to delete
     * @throws BadRequestException if the repair work entry is not found
     */
    void eliminaLavorazione(UUID id) throws BadRequestException;
}
