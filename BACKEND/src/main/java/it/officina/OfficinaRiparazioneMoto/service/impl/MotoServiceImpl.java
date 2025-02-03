package it.officina.OfficinaRiparazioneMoto.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.ClienteDao;
import it.officina.OfficinaRiparazioneMoto.dao.MotoDao;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.model.Moto;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthService authService;

    @Override
    public MotoDto salvaMoto(MotoDto moto) throws BadRequestException {
        if (motoDao.existsByTarga(moto.getTarga())) {
            throw new BadRequestException(ErrorManager.MOTO_TARGA_ESISTENTE);
        }

        Cliente clienteMoto = clienteDao.findById(moto.getIdCliente())
                .orElseThrow(() -> new BadRequestException(ErrorManager.CLIENTE_NON_ASSEGNATO_MOTO));

        Moto motoDb = modelMapper.map(moto, Moto.class);
        motoDb.setCliente(clienteMoto);
        motoDb.setUtenteReg(authService.getUtenteAutenticato());
        motoDb = motoDao.save(motoDb);

        // se serve inserire idCliente nel ritorno
        return modelMapper.map(motoDb, MotoDto.class);
    }

}
