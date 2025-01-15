/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * 
 */
public interface ClienteDao extends JpaRepository<Cliente, UUID> {

	Cliente findByEmail(String email);
	Cliente findByNome(String nome);
	Cliente findByCognome(String cognome);
	Cliente findByNomeAndCognome(String nome, String cognome);
	Cliente findByTelefono(String telefono);
}
