package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;

public interface AccettazioneService {

    void salvaAccettazione(ClienteVeicoloDto request);
    DettaglioAccettazioneDto getDettaglioAccettazione(String idRiparazione);
    List<RiparazioneModuloAccettazioneDto> getAccettazioniUtenteAutenticato();
}
