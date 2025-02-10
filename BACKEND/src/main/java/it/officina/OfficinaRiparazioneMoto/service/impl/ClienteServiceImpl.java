package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.ClienteDao;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private AuthService authService;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public ClienteDto salvaCliente(ClienteDto cliente) throws BadRequestException {
        if(cliente.getEmail() == null){
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_NON_PRESENTE);
        }

        if (clienteDao.existsByEmail(cliente.getEmail())) {
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
        }
        
        Cliente clienteDb = clienteDao.save(mapper.toEntity(cliente, authService.getUtenteDtoAutenticato()));
        return mapper.toDto(clienteDb);
    }

    @Override
    public List<ClienteDto> getAllClienti() {
        return mapper.toDtoList(clienteDao.findAll());
    }

    @Override
    public ClienteDto getClienteDtoById(UUID id) {
        return mapper.toDto(clienteDao.findById(id).orElseThrow(() -> new BadRequestException(ErrorManager.CLIENTE_NON_TROVATO)));
    }

}
