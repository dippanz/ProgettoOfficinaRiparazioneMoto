package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.DettaglioMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.RiparazioneMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;

public interface MeccanicoService {

    List<RiparazioneMeccanicoDto> getListaRiparazioneMeccanicoDto(EnumStatoRiparazione stato);

    DettaglioMeccanicoDto getDettaglioRiparazione(UUID idRiparazione);

    void prendiInCaricoRiparazioni(List<UUID> listaIdRiparazioni);

    List<RiparazioneLavorazioneDto> getListaLavorazioni(UUID idRiparazione);

    void aggiungiLavorazione(AggiungiLavorazioneDto request);

}
