/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Moto;

/**
 * 
 */
@Repository
public interface MotoDao extends JpaRepository<Moto, UUID> {

	List<Moto> findByModelloContaining(String modello);
	Moto findByTarga(String targa);
}
