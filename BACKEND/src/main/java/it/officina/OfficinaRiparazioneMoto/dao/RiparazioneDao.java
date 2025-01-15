package it.officina.OfficinaRiparazioneMoto.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.officina.OfficinaRiparazioneMoto.model.Riparazione;

public interface RiparazioneDao extends JpaRepository<Riparazione, UUID> {

}
