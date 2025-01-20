/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;

/**
 * 
 */
@Repository
public interface StatoRiparazioneDao extends JpaRepository<StatoRiparazione, Integer> {

}
