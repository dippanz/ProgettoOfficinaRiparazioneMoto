/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaClienteDto;

/**
 * 
 */
public interface ClienteService {

    ClienteDto salvaCliente(ClienteDto cliente);
    List<ClienteDto> getAllClienti();
    ClienteDto getClienteDtoById(UUID id);
    void modificaCliente(ModificaClienteDto request);
}
