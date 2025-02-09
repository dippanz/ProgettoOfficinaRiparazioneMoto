package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RiparazioneLavorazioneDao;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneLavorazioneMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneLavorazioneService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class RiparazioneLavorazioneServiceImpl implements RiparazioneLavorazioneService {

    @Autowired
    private RiparazioneLavorazioneDao riparazioneLavorazioneDao;

    @Autowired
    private RiparazioneLavorazioneMapper mapper;
    @Autowired
    private RiparazioneMapper riparazioneMapper;

    @Autowired
    private RiparazioneService riparazioneService;

    @Override
    public List<RiparazioneLavorazioneDto> getListaRiparazioneLavorazioneDto(UUID idRiparazione) {
        return mapper.toDtoList(riparazioneLavorazioneDao.findAllByRiparazioneId(idRiparazione));
    }

    @Override
    public void aggiungiLavorazione(AggiungiLavorazioneDto aggiungiLavorazioneDto) {
        RiparazioneLavorazione riparazioneLavorazione = new RiparazioneLavorazione();
        riparazioneLavorazione.setDescrizione(aggiungiLavorazioneDto.getDescrizione());
        riparazioneLavorazione.setOre(aggiungiLavorazioneDto.getOre());
        riparazioneLavorazione.setRiparazione(riparazioneMapper.toEntity(
                riparazioneService.getRiparazioneDto(aggiungiLavorazioneDto.getIdRiparazione())));

        riparazioneLavorazioneDao.save(riparazioneLavorazione);
    }

    @Override
    public void eliminaLavorazione(UUID id) throws BadRequestException {
        if(!riparazioneLavorazioneDao.existsById(id)){
            throw new BadRequestException(ErrorManager.LAVORAZIONE_NON_TROVATA);
        }

        riparazioneLavorazioneDao.deleteById(id);
    }
}
