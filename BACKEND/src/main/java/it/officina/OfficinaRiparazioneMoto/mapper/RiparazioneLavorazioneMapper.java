package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;
import jakarta.annotation.PostConstruct;

@Component
public class RiparazioneLavorazioneMapper implements BaseMapper<RiparazioneLavorazione, RiparazioneLavorazioneDto> {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(RiparazioneLavorazione.class, RiparazioneLavorazioneDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getRiparazione().getId(), RiparazioneLavorazioneDto::setIdRiparazione);
        });
    }

    @Override
    public RiparazioneLavorazioneDto toDto(RiparazioneLavorazione entity) {
        return modelMapper.map(entity, RiparazioneLavorazioneDto.class);
    }

    @Override
    public RiparazioneLavorazione toEntity(RiparazioneLavorazioneDto dto) {
        return modelMapper.map(dto, RiparazioneLavorazione.class);
    }

    @Override
    public <T> T map(RiparazioneLavorazione entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public RiparazioneLavorazioneDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, RiparazioneLavorazioneDto.class);
    }
}
