package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;
import it.officina.OfficinaRiparazioneMoto.service.PublicService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;

/**
 * Implementation of the {@link PublicService} interface.
 * <p>
 * This service provides public-facing operations to retrieve general repair
 * details.
 * It leverages the {@link RiparazioneService} to obtain repair data and
 * transforms it
 * into a format suitable for public presentation.
 * </p>
 */
@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private RiparazioneService riparazioneService;

    @Override
    public List<RiparazioneDettaglioGeneraleDto> getRiparazioneDettaglioGenerale(String codiceServizio, String targa) {
        List<RiparazioneMotoDto> listaRiparazioni = riparazioneService.getListaRiparazioneMotoDto(codiceServizio,
                targa);
        List<RiparazioneDettaglioGeneraleDto> response = new ArrayList<>();

        for (RiparazioneMotoDto rip : listaRiparazioni) {
            RiparazioneDettaglioGeneraleDto dto = new RiparazioneDettaglioGeneraleDto();
            dto.setCodiceServizio(rip.getCodiceServizio());
            dto.setTarga(rip.getTarga());
            dto.setStato(rip.getStatoRiparazione());
            dto.setDataInizio(rip.getDataInizio() != null ? rip.getDataInizio().toString() : null);
            dto.setDataFine(rip.getDataFine() != null ? rip.getDataFine().toString() : null);
            response.add(dto);
        }

        return response;
    }
}
