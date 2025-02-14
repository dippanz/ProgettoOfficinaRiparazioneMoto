package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RiparazioneLavorazioneDao;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.meccanico.AggiungiLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneLavorazioneMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneLavorazioneService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 * Service implementation for managing repair work (lavorazione) operations.
 * <p>
 * This service provides methods for retrieving, adding, and deleting repair
 * work records
 * associated with a repair. It leverages the {@link RiparazioneLavorazioneDao}
 * for data access,
 * and uses mappers to convert between entities and DTOs.
 * </p>
 */
@Service
public class RiparazioneLavorazioneServiceImpl implements RiparazioneLavorazioneService {

    @Autowired
    private EntityManager entityManager;

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

    @Transactional
    @Override
    public void aggiungiLavorazione(AggiungiLavorazioneDto aggiungiLavorazioneDto) {
        RiparazioneLavorazione riparazioneLavorazione = new RiparazioneLavorazione();
        riparazioneLavorazione.setDescrizione(aggiungiLavorazioneDto.getDescrizione());
        riparazioneLavorazione.setOre(aggiungiLavorazioneDto.getOre());
        riparazioneLavorazione.setRiparazione(riparazioneMapper.toEntity(
                riparazioneService.getRiparazioneDto(aggiungiLavorazioneDto.getIdRiparazione())));

        entityManager.refresh(riparazioneLavorazioneDao.saveAndFlush(riparazioneLavorazione));
    }

    @Override
    public void eliminaLavorazione(UUID idLavorazione) throws BadRequestException {
        if (!riparazioneLavorazioneDao.existsById(idLavorazione)) {
            throw new BadRequestException(ErrorManager.LAVORAZIONE_NON_TROVATA);
        }

        riparazioneLavorazioneDao.deleteById(idLavorazione);
    }
}
