/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Ruolo;

/**
 * 
 */
@Repository
public interface RuoloDao extends JpaRepository<Ruolo, UUID> {

	Optional<Ruolo> findByNome(String nome);
	List<Ruolo> findByNomeIn(List<String> nomi);
}
