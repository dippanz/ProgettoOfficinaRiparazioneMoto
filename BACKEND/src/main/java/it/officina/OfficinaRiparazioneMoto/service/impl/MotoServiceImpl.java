package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.ClienteDao;
import it.officina.OfficinaRiparazioneMoto.dao.MotoDao;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.model.Moto;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MotoMapper mapper;

    @Autowired
    private AuthService authService;

    @Override
    public MotoDto salvaMoto(MotoDto moto) throws BadRequestException {
        if(moto.getTarga() == null){
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
            
        Moto motoDb = motoDao.save(mapper.toEntity(moto, clienteMoto, authService.getUtenteDtoAutenticato()));
        return mapper.toDto(motoDb);
    }

    @Override
    public List<MotoDto> getAllMoto() {
        return mapper.toDtoList(motoDao.findAll());
    }

    @Override
    public MotoDto getMotoDtoById(UUID id) throws BadRequestException{
        return mapper.toDto(motoDao.findById(id).orElseThrow(() -> new BadRequestException(ErrorManager.MOTO_NON_TROVATA)));
    }

}
