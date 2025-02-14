package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;

/**
 * Repository interface for managing {@link RiparazioneLavorazione} entities.
 * <p>
 * Provides CRUD operations and additional custom query methods for retrieving
 * {@link RiparazioneLavorazione} entities based on associated repair and
 * mechanic information.
 * </p>
 */
public interface RiparazioneLavorazioneDao extends JpaRepository<RiparazioneLavorazione, UUID> {

    /**
     * Retrieves all {@link RiparazioneLavorazione} entities associated with a
     * specific repair.
     *
     * @param idRiparazione the unique identifier of the repair
     * @return a list of {@link RiparazioneLavorazione} entities for the specified
     *         repair
     */
    List<RiparazioneLavorazione> findAllByRiparazioneId(UUID idRiparazione);

    /**
     * Retrieves all {@link RiparazioneLavorazione} entities associated with repairs
     * handled
     * by a specific mechanic.
     * <p>
     * This query uses a JOIN FETCH to eagerly load the related {@code Riparazione}
     * entity.
     * </p>
     *
     * @param idUtenteMec the unique identifier of the mechanic user
     * @return a list of {@link RiparazioneLavorazione} entities associated with the
     *         specified mechanic
     */
    @Query("SELECT rl FROM RiparazioneLavorazione rl JOIN FETCH Riparazione r WHERE r.utenteMec.id = :idUtenteMec")
    List<RiparazioneLavorazione> findAllByIdUtenteMec(@Param("idUtenteMec") UUID idUtenteMec);
}
