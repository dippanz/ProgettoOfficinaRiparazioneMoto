package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Ruolo;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import jakarta.annotation.PostConstruct;

@Component
public class UtenteMapper implements BaseMapper<Utente, UtenteDto> {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(Utente.class, UtenteDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getRuoli() != null
                    ? src.getRuoli().stream().map(Ruolo::getNome).collect(Collectors.toList())
                    : null, UtenteDto::setRuoli);
        });
    }

    @Override
    public UtenteDto toDto(Utente entity) {
        return modelMapper.map(entity, UtenteDto.class);
    }

    @Override
    public Utente toEntity(UtenteDto dto) {
        return modelMapper.map(dto, Utente.class);
    }

    @Override
    public <T> T map(Utente entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public UtenteDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, UtenteDto.class);
    }
}
