package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.DettaglioMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.RiparazioneLavorazioneDettaglioDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.RiparazioneMeccanicoDto;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.service.MeccanicoService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneLavorazioneService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;

@Service
public class MeccanicoServiceImpl implements MeccanicoService {

    @Autowired
    private RiparazioneService riparazioneService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RiparazioneLavorazioneService riparazioneLavorazioneService;

    @Override
    public List<RiparazioneMeccanicoDto> getListaRiparazioneMeccanicoDto(EnumStatoRiparazione stato) {
        List<RiparazioneMotoClienteDto> riparazioni;

        switch (stato) {
            case EnumStatoRiparazione.ACCETTATO:
                // usata per tornare tutte quelle accettate verso la pagina prendi in carico
                riparazioni = riparazioneService.getListaRiparazioniMotoClienteDto(null, null, stato.getValue());
                break;

            case EnumStatoRiparazione.IN_LAVORAZIONE:
                // usata per ritornare tutte le riparazioni in lavorazione di un meccanico
                riparazioni = riparazioneService.getListaRiparazioniMotoClienteDto(null,
                        authService.getUtenteDtoAutenticato().getId(), stato.getValue());
                break;

            case EnumStatoRiparazione.COMPLETATA:
                // usata per tornare lo storico di tutte le riparazioni di un meccanico
                riparazioni = riparazioneService.getListaRiparazioniMotoClienteDto(null,
                        authService.getUtenteDtoAutenticato().getId(), stato.getValue());
                break;
            default:
                throw new IllegalArgumentException("Stato inserito non valido o non gestito");
        }

        return riparazioni.stream().map(rip -> {
            RiparazioneMeccanicoDto dto = new RiparazioneMeccanicoDto();
            dto.setIdRiparazione(rip.getId());
            dto.setCodice(rip.getCodiceServizio());
            dto.setTarga(rip.getTarga());
            dto.setEmailCliente(rip.getEmail());
            dto.setStato(rip.getStatoRiparazione());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public DettaglioMeccanicoDto getDettaglioRiparazione(UUID idRiparazione) {

        RiparazioneMotoClienteDto riparazione = riparazioneService.getRiparazioneMotoClienteDto(idRiparazione);
        List<RiparazioneLavorazioneDto> lavorazioni = riparazioneLavorazioneService
                .getListaRiparazioneLavorazioneDto(idRiparazione);

        DettaglioMeccanicoDto dto = new DettaglioMeccanicoDto();
        dto.setId(riparazione.getId());
        dto.setDescrizioneProblema(riparazione.getDescrizioneProblema());
        dto.setDataInizio(riparazione.getDataInizio());
        dto.setDataFine(riparazione.getDataFine());
        dto.setCodiceServizio(riparazione.getCodiceServizio());
        dto.setStatoRiparazione(riparazione.getStatoRiparazione());
        dto.setModello(riparazione.getModello());
        dto.setTarga(riparazione.getTarga());
        dto.setNome(riparazione.getNome());
        dto.setCognome(riparazione.getCognome());
        dto.setTelefono(riparazione.getTelefono());
        dto.setEmail(riparazione.getEmail());
        dto.setListaLavorazioni(lavorazioni.stream().map(lav -> {
            RiparazioneLavorazioneDettaglioDto dettaglioDto = new RiparazioneLavorazioneDettaglioDto();
            dettaglioDto.setDescrizioneLavorazione(lav.getDescrizione());
            dettaglioDto.setOre(lav.getOre());
            return dettaglioDto;
        }).collect(Collectors.toList()));

        // BigDecimal oreTotali = BigDecimal.ZERO;
        // for (RiparazioneLavorazioneDto lav : lavorazioni) {
        // oreTotali = oreTotali.add(lav.getOre());
        // }
        // altrimenti
        BigDecimal oreTotali = lavorazioni.stream().map(lav -> {
            return lav.getOre();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setOreTotaliLavorate(oreTotali);

        return dto;
    }

    @Override
    public void prendiInCaricoRiparazioni(List<UUID> listaIdRiparazioni) {
        listaIdRiparazioni.forEach((idRiparazione) -> {
            riparazioneService.aggiornaStatoRiparazione(idRiparazione, authService.getUtenteDtoAutenticato());
        });
    }

    @Override
    public List<RiparazioneLavorazioneDto> getListaLavorazioni(UUID idRiparazione) {
        return riparazioneLavorazioneService.getListaRiparazioneLavorazioneDto(idRiparazione);
    }

    @Override
    public void aggiungiLavorazione(AggiungiLavorazioneDto request) {
        riparazioneLavorazioneService.aggiungiLavorazione(request);
    }
}
