package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Ruolo;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import jakarta.annotation.PostConstruct;

@Component
public class UtenteMapper implements BaseMapper<Utente, UtenteDto> {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(Utente.class, UtenteDto.class)
                .addMappings(mapper -> mapper.using(ctx -> {
                    List<Ruolo> ruoli = null;
                    if (ctx.getSource() instanceof List<?>) {
                        ruoli = (List<Ruolo>) ctx.getSource();
                    }
                    return ruoli != null
                            ? ruoli.stream().map(Ruolo::getNome).collect(Collectors.toList())
                            : null;
                }).map(Utente::getRuoli, UtenteDto::setRuoli));
    }

    @Override
    public UtenteDto toDto(Utente entity) {
        return modelMapper.map(entity, UtenteDto.class);
    }

    @Override
    public Utente toEntity(UtenteDto dto) {
        return modelMapper.map(dto, Utente.class);
    }

    public Utente toEntityFrom(RegistrazioneUtenteDto dto) {
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
