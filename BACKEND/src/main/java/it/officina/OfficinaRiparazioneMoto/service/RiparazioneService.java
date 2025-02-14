package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;

/**
 * Service interface for managing repairs.
 * <p>
 * This interface defines methods for retrieving, saving, updating, and deleting
 * repair records.
 * It supports operations such as retrieving repair data for both mechanics and
 * clients,
 * updating the state of a repair, and rejecting or deleting a repair.
 * </p>
 */
public interface RiparazioneService {

    /**
     * Retrieves a list of repair DTOs for a given service code and vehicle license
     * plate.
     * <p>
     * If a service code is provided, the method searches for a single repair
     * matching both the service code and license plate.
     * Otherwise, it retrieves all repairs associated with the given license plate.
     * </p>
     *
     * @param codiceServizio the service code of the repair (optional)
     * @param targa          the license plate of the vehicle
     * @return a list of {@link RiparazioneMotoDto} representing the repair(s)
     * @throws BadRequestException if no repair is found for the provided criteria
     */
    List<RiparazioneMotoDto> getListaRiparazioneMotoDto(String codiceServizio, String targa) throws BadRequestException;

    /**
     * Saves a new accepted repair.
     * <p>
     * This method generates a unique service code for the repair, retrieves the
     * associated vehicle details,
     * and sets the initial repair state to "REGISTRATO". It also associates the
     * currently authenticated user
     * with the repair. The repair is then saved and returned as a DTO.
     * </p>
     *
     * @param riparazione the {@link RiparazioneDto} containing repair details to be
     *                    saved
     * @return the saved repair as a {@link RiparazioneDto}
     * @throws BadRequestException if the required repair state is not found
     */
    RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione) throws BadRequestException;

    /**
     * Retrieves a list of repair DTOs along with associated vehicle and customer
     * details.
     * <p>
     * The retrieval can be filtered by either the registered user ID (idUtenteReg)
     * or the mechanic user ID (idUtenteMec)
     * and optionally by one or more repair states.
     * </p>
     *
     * @param idUtenteReg the registered user ID (optional)
     * @param idUtenteMec the mechanic user ID (optional)
     * @param stati       one or more repair state identifiers to filter by
     * @return a list of {@link RiparazioneMotoClienteDto} objects representing the
     *         filtered repairs
     * @throws IllegalArgumentException if both user IDs are null and no states are
     *                                  provided
     */
    List<RiparazioneMotoClienteDto> getListaRiparazioniMotoClienteDto(UUID idUtenteReg, UUID idUtenteMec, int... stati)
            throws IllegalArgumentException;

    /**
     * Advances the status of a repair.
     * <p>
     * This method updates the repair's status to the next sequential state. If a
     * mechanic user (utenteMec)
     * is provided, it assigns the repair to that mechanic. Additionally, if the
     * repair reaches the "COMPLETATA"
     * state, the end date is set to the current time.
     * </p>
     *
     * @param idRiparazione the ID of the repair to update
     * @param utenteMec     the {@link UtenteDto} representing the mechanic
     *                      (optional)
     * @throws BadRequestException      if the repair is not found or if the repair
     *                                  is already completed
     * @throws IllegalArgumentException if an attempt is made to assign a mechanic
     *                                  when advancing to completion
     */
    void aggiornaStatoRiparazione(UUID idRiparazione, UtenteDto utenteMec)
            throws BadRequestException, IllegalArgumentException;

    /**
     * Retrieves a repair along with vehicle and customer details.
     *
     * @param idRiparazione the ID of the repair to retrieve
     * @return a {@link RiparazioneMotoClienteDto} representing the repair with
     *         vehicle and customer details
     * @throws BadRequestException if the repair is not found
     */
    RiparazioneMotoClienteDto getRiparazioneMotoClienteDto(UUID idRiparazione) throws BadRequestException;

    /**
     * Retrieves a repair by its ID.
     *
     * @param idRiparazione the ID of the repair to retrieve
     * @return a {@link RiparazioneDto} representing the repair details
     * @throws BadRequestException if the repair is not found
     */
    RiparazioneDto getRiparazioneDto(UUID idRiparazione) throws BadRequestException;

    /**
     * Deletes a repair.
     * <p>
     * A repair can only be deleted if its status is "REGISTRATO". Otherwise, an
     * exception is thrown.
     * </p>
     *
     * @param idRiparazione the ID of the repair to delete
     * @throws BadRequestException if the repair is not found or if it is not in a
     *                             deletable state
     */
    void eliminaRiparazione(UUID idRiparazione) throws BadRequestException;

    /**
     * Rejects a repair.
     * <p>
     * A repair can only be rejected if its status is "REGISTRATO". The status is
     * updated to "RIFIUTATO"
     * and the repair is saved.
     * </p>
     *
     * @param idRiparazione the ID of the repair to reject
     * @throws BadRequestException if the repair is not found or if it is not in a
     *                             rejectable state
     */
    void rifiutaRiparazione(UUID idRiparazione) throws BadRequestException;
}
