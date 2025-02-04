package it.officina.OfficinaRiparazioneMoto.service;

import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;

public interface AccettazioneService {

    RiparazioneModuloAccettazioneDto salvaAccettazione(ClienteVeicoloDto request);
    DettaglioAccettazioneDto getDettaglioAccettazione(String idRiparazione);
}
