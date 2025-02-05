package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;

public interface PublicService {

    List<RiparazioneDettaglioGeneraleDto> getRiparazioneDettaglioGenerale(String codiceServizio, String targa);
}
