package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.officina.OfficinaRiparazioneMoto.model.Riparazione;

@Repository
public interface RiparazioneDao extends JpaRepository<Riparazione, UUID> {

}
