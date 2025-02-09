package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Riparazione;

@Repository
public interface RiparazioneDao extends JpaRepository<Riparazione, UUID> {

    Optional<Riparazione> findByCodiceServizio(String codiceServizio);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto WHERE r.codiceServizio = :codiceServizio AND r.moto.targa = :targa")
    Optional<Riparazione> findByCodiceServizioAndTarga(@Param("codiceServizio") String codiceServizio, @Param("targa") String targa);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto WHERE r.moto.targa = :targa")
    List<Riparazione> findRiparazioniByTarga(@Param("targa") String targa);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto m JOIN FETCH m.cliente c WHERE m.utenteReg.id = :idUtenteReg")
    List<Riparazione> findAllByIdUtenteReg(@Param("idUtenteReg") UUID idUtenteReg);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto m JOIN FETCH m.cliente c WHERE m.utenteReg.id = :idUtenteReg AND r.stato.id IN :idStati")
    List<Riparazione> findAllByIdUtenteRegAndStati(@Param("idUtenteReg") UUID idUtenteReg, @Param("idStati") int... idStati);

    List<Riparazione> findAllByUtenteMecId(UUID idUtenteRg);

    @Query("SELECT r FROM Riparazione r WHERE r.stato.id IN :idStati")
    List<Riparazione> findAllByIdStati(@Param("idStati") int... idStati);

    @Query("SELECT r FROM Riparazione r WHERE r.stato.id IN :idStati AND r.utenteMec.id = :idUtenteMec")
    List<Riparazione> findAllByIdUtenteMecAndStati(@Param("idUtenteMec") UUID idUtenteMec, @Param("idStati") int... idStati);
}
