package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Ruolo;

/**
 * Repository interface for managing {@link Ruolo} entities.
 * <p>
 * This interface provides CRUD operations and custom query methods to retrieve
 * {@link Ruolo} instances based on their name or a list of names.
 * </p>
 */
@Repository
public interface RuoloDao extends JpaRepository<Ruolo, UUID> {

	/**
	 * Retrieves a {@link Ruolo} by its name.
	 *
	 * @param nome the name of the role
	 * @return an {@link Optional} containing the found {@link Ruolo}, or empty if
	 *         none found
	 */
	Optional<Ruolo> findByNome(String nome);

	/**
	 * Retrieves a list of {@link Ruolo} entities whose names are contained in the
	 * specified list.
	 *
	 * @param nomi a list of role names to search for
	 * @return a list of {@link Ruolo} entities matching the specified names
	 */
	List<Ruolo> findByNomeIn(List<String> nomi);
}
