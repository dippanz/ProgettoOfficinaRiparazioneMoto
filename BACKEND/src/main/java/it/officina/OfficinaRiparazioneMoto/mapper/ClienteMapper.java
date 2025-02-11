package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;

@Component
public class ClienteMapper implements BaseMapper<Cliente, ClienteDto> {

    @Autowired
    private ModelMapper modelMapper;

    /*@PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Cliente e ClienteDto
        modelMapper.typeMap(Cliente.class, ClienteDto.class).addMappings(mapper -> {
            
        });
    }*/

    @Override
    public ClienteDto toDto(Cliente entity) {
        return modelMapper.map(entity, ClienteDto.class);
    }

    @Override
    public Cliente toEntity(ClienteDto dto) {
        return modelMapper.map(dto, Cliente.class);
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
