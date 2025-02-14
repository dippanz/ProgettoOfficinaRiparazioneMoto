package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;

/**
 * Service interface for managing user operations.
 * <p>
 * This interface defines methods for registering users, retrieving available
 * roles, obtaining a list of users, and modifying user details.
 * </p>
 */
public interface UtenteService {

    /**
     * Registers a new user.
     * <p>
     * This method checks if the email or username already exists, maps the
     * registration DTO to an entity,
     * encodes the password, and saves the new user.
     * </p>
     *
     * @param utenteDto the {@link RegistrazioneUtenteDto} containing user
     *                  registration data
     * @return a {@link UtenteDto} representing the registered user with populated
     *         fields
     * @throws BadRequestException if the email or username already exists
     */
    UtenteDto registraUtente(RegistrazioneUtenteDto utenteDto) throws BadRequestException;

    /**
     * Retrieves a list of available user roles, excluding the 'ADMIN' role.
     *
     * @return a list of role names as {@link String}
     */
    List<String> getRuoliUtente();

    /**
     * Retrieves a list of users excluding those with the 'ADMIN' role.
     *
     * @return a list of {@link UtenteDto} objects representing the users
     */
    List<UtenteDto> getListaUtenteDto();

    /**
     * Modifies the details of an existing user.
     * <p>
     * Updates the user's first name, last name, email, telephone, and username if
     * they are provided.
     * Validates that the new email or username (if provided) does not already exist
     * for another user.
     * </p>
     *
     * @param request the {@link ModificaUtenteDto} containing the modifications to
     *                be applied
     * @throws BadRequestException if the specified user is not found or if the new
     *                             email/username already exists for another user
     */
    void modificaUtente(ModificaUtenteDto request) throws BadRequestException;
}
