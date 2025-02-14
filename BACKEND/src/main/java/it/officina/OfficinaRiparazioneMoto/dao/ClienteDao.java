package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * Repository interface for managing {@link Cliente} entities.
 * <p>
 * This interface provides CRUD operations and additional query methods
 * to retrieve {@link Cliente} instances based on various attributes.
 * </p>
 */
@Repository
public interface ClienteDao extends JpaRepository<Cliente, UUID> {

    /**
     * Retrieves a {@link Cliente} by its email address.
     *
     * @param email the email address of the cliente
     * @return an {@link Optional} containing the found {@link Cliente}, or empty if
     *         none found
     */
    Optional<Cliente> findByEmail(String email);

    /**
     * Retrieves a {@link Cliente} by its first name.
     *
     * @param nome the first name of the cliente
     * @return an {@link Optional} containing the found {@link Cliente}, or empty if
     *         none found
     */
    Optional<Cliente> findByNome(String nome);

    /**
     * Retrieves a {@link Cliente} by its last name.
     *
     * @param cognome the last name of the cliente
     * @return an {@link Optional} containing the found {@link Cliente}, or empty if
     *         none found
     */
    Optional<Cliente> findByCognome(String cognome);

    /**
     * Retrieves a {@link Cliente} by its first name and last name.
     *
     * @param nome    the first name of the cliente
     * @param cognome the last name of the cliente
     * @return an {@link Optional} containing the found {@link Cliente}, or empty if
     *         none found
     */
    Optional<Cliente> findByNomeAndCognome(String nome, String cognome);

    /**
     * Retrieves a {@link Cliente} by its telephone number.
     *
     * @param telefono the telephone number of the cliente
     * @return an {@link Optional} containing the found {@link Cliente}, or empty if
     *         none found
     */
    Optional<Cliente> findByTelefono(String telefono);

    /**
     * Checks if a {@link Cliente} with the specified email exists.
     *
     * @param email the email address to check
     * @return {@code true} if a {@link Cliente} with the given email exists,
     *         {@code false} otherwise
     */
    boolean existsByEmail(String email);
}
