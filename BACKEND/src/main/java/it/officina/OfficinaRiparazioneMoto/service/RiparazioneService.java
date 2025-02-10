/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;

/**
 * 
 */
public interface RiparazioneService {
    List<RiparazioneMotoDto> getListaRiparazioneMotoDto(String codiceServizio, String targa);
    RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione);
    List<RiparazioneMotoClienteDto> getListaRiparazioniMotoClienteDto(UUID idUtenteReg, UUID idUtenteMec, int... stati);
    void aggiornaStatoRiparazione(UUID idRiparazione, UtenteDto utenteMec);
    RiparazioneMotoClienteDto getRiparazioneMotoClienteDto(UUID idRiparazione);
    RiparazioneDto getRiparazioneDto(UUID idRiparazione);
}
