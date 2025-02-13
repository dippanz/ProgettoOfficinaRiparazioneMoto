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

	@Query("SELECT u FROM Utente u JOIN FETCH u.ruoli WHERE u.email = :email")
    Optional<Utente> findByEmailWithRoles(@Param("email") String email);

    @Query("SELECT u FROM Utente u JOIN FETCH u.ruoli WHERE u.username = :username")
    Optional<Utente> findByUsernameWithRoles(@Param("username") String username);

	@Query("SELECT u FROM Utente u WHERE NOT EXISTS (SELECT r FROM u.ruoli r WHERE r.nome = 'ADMIN')")
    List<Utente> findAllExceptAdmin();
}
