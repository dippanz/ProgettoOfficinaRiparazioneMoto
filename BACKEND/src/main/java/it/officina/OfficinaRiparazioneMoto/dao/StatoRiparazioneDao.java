/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;

/**
 * Repository interface for managing {@link StatoRiparazione} entities.
 * <p>
 * This interface provides CRUD operations and custom query methods to retrieve
 * {@link StatoRiparazione} instances based on their name or a list of names.
 * </p>
 */
@Repository
public interface StatoRiparazioneDao extends JpaRepository<StatoRiparazione, Integer> {

}
