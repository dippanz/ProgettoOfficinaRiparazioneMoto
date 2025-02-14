package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * Repository interface for managing {@link Utente} entities.
 * <p>
 * Provides CRUD operations and custom query methods for retrieving user
 * entities
 * based on various attributes such as email, username, name, and phone number.
 * Additionally, it offers methods to fetch users along with their associated
 * roles
 * and to retrieve non-admin users.
 * </p>
 */
@Repository
public interface UtenteDao extends JpaRepository<Utente, UUID> {

	/**
	 * Retrieves a {@link Utente} by its email.
	 *
	 * @param email the email of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByEmail(String email);

	/**
	 * Retrieves a {@link Utente} by its username.
	 *
	 * @param username the username of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByUsername(String username);

	/**
	 * Retrieves a {@link Utente} by its first name.
	 *
	 * @param nome the first name of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByNome(String nome);

	/**
	 * Retrieves a {@link Utente} by its last name.
	 *
	 * @param cognome the last name of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByCognome(String cognome);

	/**
	 * Retrieves a {@link Utente} by its first name and last name.
	 *
	 * @param nome    the first name of the user
	 * @param cognome the last name of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByNomeAndCognome(String nome, String cognome);

	/**
	 * Retrieves a {@link Utente} by its telephone number.
	 *
	 * @param telefono the telephone number of the user
	 * @return an {@link Optional} containing the found {@link Utente}, or empty if
	 *         none found
	 */
	Optional<Utente> findByTelefono(String telefono);

	/**
	 * Checks if a {@link Utente} with the specified email exists.
	 *
	 * @param email the email to check
	 * @return {@code true} if a user with the given email exists, {@code false}
	 *         otherwise
	 */
	boolean existsByEmail(String email);

	/**
	 * Checks if a {@link Utente} with the specified username exists.
	 *
	 * @param username the username to check
	 * @return {@code true} if a user with the given username exists, {@code false}
	 *         otherwise
	 */
	boolean existsByUsername(String username);

	/**
	 * Retrieves a {@link Utente} along with its associated roles using the user's
	 * email.
	 * <p>
	 * This method fetches the user entity and eagerly loads its associated roles.
	 * </p>
	 *
	 * @param email the email of the user
	 * @return an {@link Optional} containing the found {@link Utente} with roles,
	 *         or empty if none found
	 */
	@Query("SELECT u FROM Utente u JOIN FETCH u.ruoli WHERE u.email = :email")
	Optional<Utente> findByEmailWithRoles(@Param("email") String email);

	/**
	 * Retrieves a {@link Utente} along with its associated roles using the user's
	 * username.
	 * <p>
	 * This method fetches the user entity and eagerly loads its associated roles.
	 * </p>
	 *
	 * @param username the username of the user
	 * @return an {@link Optional} containing the found {@link Utente} with roles,
	 *         or empty if none found
	 */
	@Query("SELECT u FROM Utente u JOIN FETCH u.ruoli WHERE u.username = :username")
	Optional<Utente> findByUsernameWithRoles(@Param("username") String username);

	/**
	 * Retrieves all {@link Utente} entities that do not have the 'ADMIN' role.
	 * <p>
	 * This method returns a list of users excluding those who have an associated
	 * role with the name 'ADMIN'.
	 * </p>
	 *
	 * @return a list of {@link Utente} entities without the 'ADMIN' role
	 */
	@Query("SELECT u FROM Utente u WHERE NOT EXISTS (SELECT r FROM u.ruoli r WHERE r.nome = 'ADMIN')")
	List<Utente> findAllExceptAdmin();
}
