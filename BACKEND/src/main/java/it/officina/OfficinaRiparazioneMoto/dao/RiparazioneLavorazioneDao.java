package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;

public interface RiparazioneLavorazioneDao extends JpaRepository<RiparazioneLavorazione, UUID> {

    // @Query("SELECT rl FROM RiparazioneLavorazione rl WHERE rl.riparazione.id = :idRiparazione")
    // RiparazioneLavorazione findByIdRiparazione(@Param("idRiparazione") UUID idRiparazione);
    List<RiparazioneLavorazione> findAllByRiparazioneId(UUID idRiparazione);

    @Query("SELECT rl FROM RiparazioneLavorazione rl JOIN FETCH Riparazione r WHERE r.utenteMec.id = :idUtenteMec")
    List<RiparazioneLavorazione> findAllByIdUtenteMec(@Param("idUtenteMec") UUID idUtenteMec);
}
