package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.DettaglioMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.RiparazioneMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;

/**
 * Service interface for mechanic operations.
 * <p>
 * This interface defines methods for mechanics to manage repair processes,
 * including retrieving repair lists filtered by state, getting repair details,
 * taking charge of repairs, managing work items, and adding new work items.
 * </p>
 */
public interface MeccanicoService {

    /**
     * Retrieves a list of repairs for mechanics filtered by the specified repair
     * state.
     * <p>
     * Depending on the state provided:
     * <ul>
     * <li>If state is ACCETTATO, it returns all repairs with the accepted state for
     * the "prendi in carico" page.</li>
     * <li>If state is IN_LAVORAZIONE or COMPLETATA, it returns repairs assigned to
     * the currently authenticated mechanic.</li>
     * </ul>
     * </p>
     *
     * @param stato the repair state to filter by
     * @return a list of {@link RiparazioneMeccanicoDto} objects representing the
     *         filtered repairs
     * @throws IllegalArgumentException if the provided state is not valid or not
     *                                  handled
     */
    List<RiparazioneMeccanicoDto> getListaRiparazioneMeccanicoDto(EnumStatoRiparazione stato)
            throws IllegalArgumentException;

    /**
     * Retrieves detailed repair information for a given repair ID from the
     * mechanic's perspective.
     * <p>
     * This method obtains the repair data along with associated repair work
     * (lavorazioni)
     * and computes the total hours worked.
     * </p>
     *
     * @param idRiparazione the unique identifier of the repair
     * @return a {@link DettaglioMeccanicoDto} containing detailed repair and work
     *         information
     */
    DettaglioMeccanicoDto getDettaglioRiparazione(UUID idRiparazione);

    /**
     * Assigns repairs to the currently authenticated mechanic.
     * <p>
     * For each repair ID provided, the method advances the repair's state by
     * updating it via the
     * {@link RiparazioneService}, assigning the authenticated mechanic.
     * </p>
     *
     * @param listaIdRiparazioni a list of repair IDs to be taken in charge by the
     *                           mechanic
     */
    void prendiInCaricoRiparazioni(List<UUID> listaIdRiparazioni);

    /**
     * Retrieves a list of repair work (lavorazioni) DTOs for a given repair.
     *
     * @param idRiparazione the unique identifier of the repair
     * @return a list of {@link RiparazioneLavorazioneDto} objects representing the
     *         repair work items
     */
    List<RiparazioneLavorazioneDto> getListaLavorazioni(UUID idRiparazione);

    /**
     * Adds a new repair work (lavorazione) entry to a repair.
     *
     * @param request the {@link AggiungiLavorazioneDto} containing details of the
     *                work to be added
     */
    void aggiungiLavorazione(AggiungiLavorazioneDto request);
}
