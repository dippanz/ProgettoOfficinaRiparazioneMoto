package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.ClienteDao;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaClienteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

/**
 * Implementation of the {@link ClienteService} interface.
 * <p>
 * This service provides operations for managing client data, including saving
 * new clients,
 * retrieving client lists, retrieving a specific client by ID, and modifying
 * client details.
 * </p>
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public ClienteDto salvaCliente(ClienteDto cliente) throws BadRequestException {
        if (cliente.getEmail() == null) {
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_NON_PRESENTE);
        }

        if (clienteDao.existsByEmail(cliente.getEmail())) {
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
        }

        Cliente clienteDb = clienteDao.save(mapper.toEntity(cliente));
        return mapper.toDto(clienteDb);
    }

    @Override
    public List<ClienteDto> getAllClienti() {
        return mapper.toDtoList(clienteDao.findAll());
    }

    @Override
    public ClienteDto getClienteDtoById(UUID id) throws BadRequestException {
        return mapper.toDto(
                clienteDao.findById(id).orElseThrow(() -> new BadRequestException(ErrorManager.CLIENTE_NON_TROVATO)));
    }

    @Override
    public void modificaCliente(ModificaClienteDto request) throws BadRequestException {
        Cliente cliente = clienteDao.findById(request.getId())
                .orElseThrow(() -> new BadRequestException(ErrorManager.CLIENTE_NON_TROVATO));

        if (request.getNome() != null) {
            cliente.setNome(request.getNome());
        }
        if (request.getCognome() != null) {
            cliente.setCognome(request.getCognome());
        }
        if (request.getEmail() != null) {
            Optional<Cliente> altroCliente = clienteDao.findByEmail(request.getEmail());
            if (altroCliente.isPresent() && !altroCliente.get().getId().equals(request.getId())) {
                throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
            }
            cliente.setEmail(request.getEmail());
        }
        if (request.getTelefono() != null) {
            cliente.setTelefono(request.getTelefono());
        }
        clienteDao.save(cliente);
    }
}
