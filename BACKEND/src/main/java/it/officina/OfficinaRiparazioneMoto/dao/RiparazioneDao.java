package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Riparazione;

/**
 * Repository interface for managing {@link Riparazione} entities.
 * <p>
 * Provides CRUD operations and custom query methods for retrieving repair
 * records based
 * on various criteria such as service code, vehicle license plate, and
 * associated user IDs.
 * </p>
 */
@Repository
public interface RiparazioneDao extends JpaRepository<Riparazione, UUID> {

    /**
     * Retrieves a {@link Riparazione} by its service code.
     *
     * @param codiceServizio the service code of the repair
     * @return an {@link Optional} containing the found {@link Riparazione}, or
     *         empty if none found
     */
    Optional<Riparazione> findByCodiceServizio(String codiceServizio);

    /**
     * Retrieves a {@link Riparazione} by its service code and the vehicle's license
     * plate.
     * <p>
     * This query fetches the associated
     * {@link it.officina.OfficinaRiparazioneMoto.model.Moto}
     * entity along with the repair.
     * </p>
     *
     * @param codiceServizio the service code of the repair
     * @param targa          the license plate of the vehicle
     * @return an {@link Optional} containing the found {@link Riparazione}, or
     *         empty if none found
     */
    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto WHERE r.codiceServizio = :codiceServizio AND r.moto.targa = :targa")
    Optional<Riparazione> findByCodiceServizioAndTarga(@Param("codiceServizio") String codiceServizio,
            @Param("targa") String targa);

    /**
     * Retrieves a list of {@link Riparazione} entities associated with a specific
     * vehicle license plate.
     * <p>
     * This query fetches the associated
     * {@link it.officina.OfficinaRiparazioneMoto.model.Moto}
     * entity along with each repair.
     * </p>
     *
     * @param targa the license plate of the vehicle
     * @return a list of {@link Riparazione} entities for the specified vehicle
     */
    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto WHERE r.moto.targa = :targa")
    List<Riparazione> findRiparazioniByTarga(@Param("targa") String targa);

    /**
     * Retrieves all {@link Riparazione} entities registered by a specific user.
     *
     * @param idUtenteReg the unique identifier of the registering user
     * @return a list of {@link Riparazione} entities associated with the specified
     *         user
     */
    List<Riparazione> findAllByUtenteRegId(@Param("idUtenteReg") UUID idUtenteReg);

    /**
     * Retrieves all {@link Riparazione} entities registered by a specific user that
     * are in any of the specified states.
     * <p>
     * The results are ordered by the state ID.
     * </p>
     *
     * @param idUtenteReg the unique identifier of the registering user
     * @param idStati     one or more state IDs to filter the repairs
     * @return a list of {@link Riparazione} entities matching the criteria
     */
    @Query("SELECT r FROM Riparazione r WHERE r.utenteReg.id = :idUtenteReg AND r.stato.id IN :idStati ORDER BY r.stato.id")
    List<Riparazione> findAllByIdUtenteRegAndStati(@Param("idUtenteReg") UUID idUtenteReg,
            @Param("idStati") int... idStati);

    /**
     * Retrieves all {@link Riparazione} entities assigned to a specific mechanic.
     *
     * @param idUtenteMec the unique identifier of the mechanic user
     * @return a list of {@link Riparazione} entities associated with the specified
     *         mechanic
     */
    List<Riparazione> findAllByUtenteMecId(UUID idUtenteMec);

    /**
     * Retrieves all {@link Riparazione} entities that are in any of the specified
     * states.
     *
     * @param idStati one or more state IDs to filter the repairs
     * @return a list of {@link Riparazione} entities matching the specified states
     */
    @Query("SELECT r FROM Riparazione r WHERE r.stato.id IN :idStati")
    List<Riparazione> findAllByIdStati(@Param("idStati") int... idStati);

    /**
     * Retrieves all {@link Riparazione} entities assigned to a specific mechanic
     * and that are in any of the specified states.
     *
     * @param idUtenteMec the unique identifier of the mechanic user
     * @param idStati     one or more state IDs to filter the repairs
     * @return a list of {@link Riparazione} entities matching the criteria
     */
    @Query("SELECT r FROM Riparazione r WHERE r.stato.id IN :idStati AND r.utenteMec.id = :idUtenteMec")
    List<Riparazione> findAllByIdUtenteMecAndStati(@Param("idUtenteMec") UUID idUtenteMec,
            @Param("idStati") int... idStati);
}
