package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;

@Component
public class ClienteMapper implements BaseMapper<Cliente, ClienteDto> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UtenteMapper utenteMapper;

    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Cliente e ClienteDto
        modelMapper.typeMap(Cliente.class, ClienteDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getUtenteReg().getId(), ClienteDto::setUtenteReg);
        });
    }

    @Override
    public ClienteDto toDto(Cliente entity) {
        return modelMapper.map(entity, ClienteDto.class);
    }

    @Override
    public Cliente toEntity(ClienteDto dto) {
        return modelMapper.map(dto, Cliente.class);
    }

    public Cliente toEntity(ClienteDto dto, UtenteDto utenteReg) {
        Cliente cliente = toEntity(dto);
        if (utenteReg != null) {
            cliente.setUtenteReg(utenteMapper.toEntity(utenteReg));
        }
        return cliente;
    }

    @Override
    public <T> T map(Cliente entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public ClienteDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, ClienteDto.class);
    }

}
