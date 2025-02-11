package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.model.Moto;

@Component
public class MotoMapper implements BaseMapper<Moto, MotoDto> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClienteMapper clienteMapper;

    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Moto e MotoDto
        modelMapper.typeMap(Moto.class, MotoDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCliente().getId(), MotoDto::setIdCliente);
        });
    }

    @Override
    public MotoDto toDto(Moto entity) {
        return modelMapper.map(entity, MotoDto.class);
    }

    @Override
    public Moto toEntity(MotoDto dto) {
        return modelMapper.map(dto, Moto.class);
    }

    public Moto toEntity(MotoDto dto, ClienteDto cliente) {
        Moto moto = toEntity(dto);
        if (cliente != null) {
            moto.setCliente(clienteMapper.toEntity(cliente));
        }
        return moto;
    }

    @Override
    public <T> T map(Moto entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public MotoDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, MotoDto.class);
    }
}
