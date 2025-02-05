/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

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
    List<RiparazioneMotoDto> getRiparazioneWithMoto(String codiceServizio, String targa);
    RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione);
    List<RiparazioneMotoClienteDto> getRiparazioneWithMotoAndCliente();
}
