package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.MotoDao;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaMotoDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.model.Moto;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

/**
 * Implementation of the {@link MotoService} interface.
 * <p>
 * This service provides operations for managing motorcycles, including saving a
 * new motorcycle,
 * retrieving motorcycles, and modifying motorcycle details. It uses
 * {@link MotoDao} for data access,
 * and {@link MotoMapper} to convert between entity and DTO objects. It also
 * interacts with {@link ClienteService}
 * to retrieve client information associated with a motorcycle.
 * </p>
 */
@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MotoMapper mapper;

    @Override
    public MotoDto salvaMoto(MotoDto moto) throws BadRequestException {
        if (moto.getTarga() == null) {
            throw new BadRequestException(ErrorManager.MOTO_TARGA_NON_PRESENTE);
        }

        if (motoDao.existsByTarga(moto.getTarga())) {
            throw new BadRequestException(ErrorManager.MOTO_TARGA_ESISTENTE);
        }

        ClienteDto clienteMoto;
        try {
            clienteMoto = clienteService.getClienteDtoById(moto.getIdCliente());
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorManager.CLIENTE_NON_ASSEGNATO_MOTO);
        }

        Moto motoDb = motoDao.save(mapper.toEntity(moto, clienteMoto));
        return mapper.toDto(motoDb);
    }

    @Override
    public List<MotoDto> getAllMoto() {
        return mapper.toDtoList(motoDao.findAll());
    }

    @Override
    public MotoDto getMotoDtoById(UUID id) throws BadRequestException {
        return mapper
                .toDto(motoDao.findById(id).orElseThrow(() -> new BadRequestException(ErrorManager.MOTO_NON_TROVATA)));
    }

    @Override
    public List<MotoClienteDto> getListMotoClienteDto() {
        return mapper.entityToListaMotoClienteDto(motoDao.findAll());
    }

    @Override
    public void modificaMoto(ModificaMotoDto request) throws BadRequestException {
        Moto moto = motoDao.findById(request.getId())
                .orElseThrow(() -> new BadRequestException(ErrorManager.MOTO_NON_TROVATA));

        if (request.getModello() != null) {
            moto.setModello(request.getModello());
        }
        if (request.getTarga() != null) {
            Optional<Moto> altraMoto = motoDao.findByTarga(request.getTarga());
            if (altraMoto.isPresent() && !altraMoto.get().getId().equals(request.getId())) {
                throw new BadRequestException(ErrorManager.MOTO_TARGA_ESISTENTE);
            }
            moto.setTarga(request.getTarga());
        }
        motoDao.save(moto);
    }
}
