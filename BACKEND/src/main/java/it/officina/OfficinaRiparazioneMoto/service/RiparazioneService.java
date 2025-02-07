/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;

/**
 * 
 */
public interface RiparazioneService {
    List<RiparazioneMotoDto> getListaRiparazioneMotoDto(String codiceServizio, String targa);
    RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione);
    List<RiparazioneMotoClienteDto> getListaRiparazioniMotoClienteDto();
    void aggiornaStatoRiparazione(UUID idRiparazione, int registrato);
    RiparazioneMotoClienteDto getRiparazioneMotoClienteDto(UUID idRiparazione);
}
