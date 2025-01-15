/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.officina.OfficinaRiparazioneMoto.model.Moto;

/**
 * 
 */
public interface MotoDao extends JpaRepository<Moto, UUID> {

	List<Moto> findByModelloContaining(String modello);
	Moto findByTarga(String targa);
}
