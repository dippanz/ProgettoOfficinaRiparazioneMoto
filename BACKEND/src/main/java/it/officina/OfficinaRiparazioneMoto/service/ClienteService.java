package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaClienteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * Service interface for managing clients.
 * <p>
 * This interface defines methods for saving, retrieving, and modifying client
 * data.
 * </p>
 */
public interface ClienteService {

    /**
     * Saves a new client.
     * <p>
     * The method validates that the client's email is provided and not already in
     * use. It then converts
     * the {@link ClienteDto} to a {@link Cliente} entity, saves it in the database,
     * and returns the saved
     * client as a DTO.
     * </p>
     *
     * @param cliente the {@link ClienteDto} containing client data to be saved
     * @return the saved {@link ClienteDto} with populated fields
     * @throws BadRequestException if the client's email is missing or already
     *                             exists
     */
    ClienteDto salvaCliente(ClienteDto cliente) throws BadRequestException;

    /**
     * Retrieves a list of all clients.
     *
     * @return a list of {@link ClienteDto} objects representing all clients
     */
    List<ClienteDto> getAllClienti();

    /**
     * Retrieves a client by its unique identifier.
     *
     * @param id the unique identifier of the client
     * @return a {@link ClienteDto} representing the client data
     * @throws BadRequestException if the client is not found
     */
    ClienteDto getClienteDtoById(UUID id) throws BadRequestException;

    /**
     * Modifies the details of an existing client.
     * <p>
     * The method updates the client's first name, last name, email, and telephone
     * if they are provided.
     * It also ensures that the new email is not already in use by another client.
     * </p>
     *
     * @param request the {@link ModificaClienteDto} containing the modifications to
     *                apply
     * @throws BadRequestException if the client is not found or if the new email
     *                             already exists for another client
     */
    void modificaCliente(ModificaClienteDto request) throws BadRequestException;
}
