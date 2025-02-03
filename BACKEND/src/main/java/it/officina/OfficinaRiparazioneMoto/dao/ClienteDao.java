/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * 
 */
@Repository
public interface ClienteDao extends JpaRepository<Cliente, UUID> {

	Optional<Cliente> findByEmail(String email);
	Optional<Cliente> findByNome(String nome);
	Optional<Cliente> findByCognome(String cognome);
	Optional<Cliente> findByNomeAndCognome(String nome, String cognome);
	Optional<Cliente> findByTelefono(String telefono);
    boolean existsByEmail(String email);
}
