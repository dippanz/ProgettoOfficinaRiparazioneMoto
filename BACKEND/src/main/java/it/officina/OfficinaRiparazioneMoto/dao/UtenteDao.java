/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * 
 */
@Repository
public interface UtenteDao extends JpaRepository<Utente, UUID> {

	Optional<Utente> findByEmail(String email);
	Optional<Utente> findByUsername(String username);
	Optional<Utente> findByNome(String nome);
	Optional<Utente> findByCognome(String cognome);
	Optional<Utente> findByNomeAndCognome(String nome, String cognome);
	Optional<Utente> findByTelefono(String telefono);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}
