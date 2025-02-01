/**
 * 
 */
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
 * 
 */
@Repository
public interface MotoDao extends JpaRepository<Moto, UUID> {

	List<Moto> findByModelloContaining(String modello);
	Optional<Moto> findByTarga(String targa);

	@Query("SELECT m FROM Moto m JOIN FETCH m.riparazioni WHERE m.targa = :targa")
	Optional<Moto> findByTargaWithRiparazioni(@Param("targa") String targa);
}
