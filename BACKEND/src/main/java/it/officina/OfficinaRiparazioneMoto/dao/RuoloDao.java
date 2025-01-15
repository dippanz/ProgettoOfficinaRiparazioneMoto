/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.officina.OfficinaRiparazioneMoto.model.Ruolo;

/**
 * 
 */
public interface RuoloDao extends JpaRepository<Ruolo, UUID> {

	Ruolo findByNome(String nome);
}
