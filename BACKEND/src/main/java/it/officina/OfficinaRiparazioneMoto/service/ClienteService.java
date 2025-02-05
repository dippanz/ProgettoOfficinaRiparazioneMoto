/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * 
 */
public interface ClienteService {

    ClienteDto salvaCliente(ClienteDto cliente);
    List<ClienteDto> getAllClienti();
    ClienteDto getClienteDtoById(UUID id);
}
