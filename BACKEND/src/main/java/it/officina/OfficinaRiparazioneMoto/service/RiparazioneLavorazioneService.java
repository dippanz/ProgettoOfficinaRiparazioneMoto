package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;

public interface RiparazioneLavorazioneService {

    List<RiparazioneLavorazioneDto> getListaRiparazioneLavorazioneDto(UUID idRiparazione);

    void aggiungiLavorazione(AggiungiLavorazioneDto aggiungiLavorazioneDto);

    void eliminaLavorazione(UUID id) throws BadRequestException;

}
