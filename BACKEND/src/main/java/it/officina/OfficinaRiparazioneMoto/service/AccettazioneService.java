package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;

public interface AccettazioneService {

    void salvaAccettazione(ClienteVeicoloDto request);
    DettaglioAccettazioneDto getDettaglioAccettazione(UUID idRiparazione);
    List<RiparazioneModuloAccettazioneDto> getListaRiparazioniModuloAccettazioneDto();
    void accettaRiparazione(UUID idRiparazione);
}
