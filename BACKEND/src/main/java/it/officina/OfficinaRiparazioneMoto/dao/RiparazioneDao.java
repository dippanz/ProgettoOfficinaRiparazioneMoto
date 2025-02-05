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
    Optional<Riparazione> findByCodiceServizioAndTargaWithMoto(@Param("codiceServizio") String codiceServizio, @Param("targa") String targa);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto WHERE r.moto.targa = :targa")
    List<Riparazione> findRiparazioniByTarga(@Param("targa") String targa);

    @Query("SELECT r FROM Riparazione r JOIN FETCH r.moto m JOIN FETCH m.cliente c WHERE m.utenteReg = :idUtenteReg")
    List<Riparazione> findAllByUtenteRegWithMotoAndCliente(@Param("idUtenteReg") UUID idUtenteReg);
}
