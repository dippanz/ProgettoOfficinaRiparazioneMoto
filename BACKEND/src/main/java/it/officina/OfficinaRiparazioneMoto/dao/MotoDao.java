package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Moto;

/**
 * Repository interface for managing {@link Moto} entities.
 * <p>
 * Provides CRUD operations and additional query methods to retrieve
 * {@link Moto} instances
 * based on various attributes such as model and license plate. Custom queries
 * are defined
 * to fetch associated repair data as needed.
 * </p>
 */
@Repository
public interface MotoDao extends JpaRepository<Moto, UUID> {

	/**
	 * Retrieves a list of {@link Moto} whose model contains the specified keyword.
	 *
	 * @param modello the keyword to search within the model name
	 * @return a list of {@link Moto} entities with matching model names
	 */
	List<Moto> findByModelloContaining(String modello);

	/**
	 * Retrieves a {@link Moto} by its license plate.
	 *
	 * @param targa the license plate of the moto
	 * @return an {@link Optional} containing the found {@link Moto}, or empty if
	 *         none found
	 */
	Optional<Moto> findByTarga(String targa);

	/**
	 * Retrieves a {@link Moto} along with its associated repairs using a JOIN FETCH
	 * query.
	 * <p>
	 * This method fetches the moto entity and eagerly loads its associated repair
	 * entities.
	 * </p>
	 *
	 * @param targa the license plate of the moto
	 * @return an {@link Optional} containing the found {@link Moto} with its
	 *         repairs, or empty if none found
	 */
	@Query("SELECT m FROM Moto m JOIN FETCH m.riparazioni WHERE m.targa = :targa")
	Optional<Moto> findByTargaWithRiparazioni(@Param("targa") String targa);

	/**
	 * Checks if a {@link Moto} with the specified license plate exists.
	 *
	 * @param targa the license plate to check
	 * @return {@code true} if a {@link Moto} with the given license plate exists,
	 *         {@code false} otherwise
	 */
	boolean existsByTarga(String targa);
}
