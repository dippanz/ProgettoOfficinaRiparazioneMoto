/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * 
 */
public interface UtenteDao extends JpaRepository<Utente, UUID> {

	Cliente findByEmail(String email);
	Cliente findByUserName(String userName);
	Cliente findByNome(String nome);
	Cliente findByCognome(String cognome);
	Cliente findByNomeAndCognome(String nome, String cognome);
	Cliente findByTelefono(String telefono);
}
