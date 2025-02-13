/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.dto.MotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;

/**
 * 
 */
public interface MotoService {

    MotoDto salvaMoto(MotoDto moto);

    List<MotoDto> getAllMoto();

    MotoDto getMotoDtoById(UUID id);

    List<MotoClienteDto> getListMotoClienteDto();

}
