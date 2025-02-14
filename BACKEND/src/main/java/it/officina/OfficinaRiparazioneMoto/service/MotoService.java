package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.MotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaMotoDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;

/**
 * Service interface for managing motorcycles.
 * <p>
 * This interface defines methods for saving, retrieving, and modifying
 * motorcycle data.
 * It also includes methods for obtaining motorcycle data along with associated
 * client information.
 * </p>
 */
public interface MotoService {

    /**
     * Saves a new motorcycle.
     * <p>
     * The method verifies that the license plate is provided and not already
     * present in the system.
     * It then retrieves the associated client data and saves the motorcycle entity.
     * The saved entity is
     * converted back to a {@link MotoDto} and returned.
     * </p>
     *
     * @param moto the {@link MotoDto} containing the motorcycle data to save
     * @return the saved {@link MotoDto} with populated fields
     * @throws BadRequestException if the license plate is missing, already exists,
     *                             or if the client cannot be found
     */
    MotoDto salvaMoto(MotoDto moto) throws BadRequestException;

    /**
     * Retrieves all motorcycles.
     *
     * @return a list of {@link MotoDto} objects representing all motorcycles
     */
    List<MotoDto> getAllMoto();

    /**
     * Retrieves a motorcycle by its unique identifier.
     *
     * @param id the unique identifier of the motorcycle
     * @return a {@link MotoDto} representing the motorcycle data
     * @throws BadRequestException if the motorcycle is not found
     */
    MotoDto getMotoDtoById(UUID id) throws BadRequestException;

    /**
     * Retrieves a list of motorcycles along with associated client information.
     *
     * @return a list of {@link MotoClienteDto} objects representing motorcycles
     *         with client details
     */
    List<MotoClienteDto> getListMotoClienteDto();

    /**
     * Modifies the details of an existing motorcycle.
     * <p>
     * The method updates the motorcycle's model and license plate based on the
     * provided data.
     * It checks for duplicate license plates to prevent conflicts.
     * </p>
     *
     * @param request the {@link ModificaMotoDto} containing the modifications to
     *                apply
     * @throws BadRequestException if the motorcycle is not found or if the new
     *                             license plate already exists for another
     *                             motorcycle
     */
    void modificaMoto(ModificaMotoDto request) throws BadRequestException;
}
