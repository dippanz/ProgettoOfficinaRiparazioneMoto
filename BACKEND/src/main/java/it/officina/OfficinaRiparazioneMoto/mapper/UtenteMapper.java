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

/**
 * Mapper component for converting between {@link Utente} entities and their
 * corresponding
 * {@link UtenteDto} Data Transfer Objects.
 * <p>
 * This mapper leverages the {@link ModelMapper} library to automatically map
 * fields between
 * the {@code Utente} model and {@code UtenteDto}. A custom mapping is
 * configured to convert
 * the list of {@link Ruolo} objects into a list of role names for the DTO.
 * </p>
 */
@Component
public class UtenteMapper implements BaseMapper<Utente, UtenteDto> {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Configures custom mappings for converting between {@link Utente} and
     * {@link UtenteDto}.
     * <p>
     * Specifically, it maps the list of {@link Ruolo} entities from the
     * {@code Utente} model
     * to a list of role names in the {@code UtenteDto}.
     * </p>
     */
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

    /**
     * Converts a {@link Utente} entity to a {@link UtenteDto}.
     *
     * @param entity the {@link Utente} entity to convert
     * @return the corresponding {@link UtenteDto}
     */
    @Override
    public UtenteDto toDto(Utente entity) {
        return modelMapper.map(entity, UtenteDto.class);
    }

    /**
     * Converts a {@link UtenteDto} to a {@link Utente} entity.
     *
     * @param dto the {@link UtenteDto} to convert
     * @return the corresponding {@link Utente} entity
     */
    @Override
    public Utente toEntity(UtenteDto dto) {
        return modelMapper.map(dto, Utente.class);
    }

    /**
     * Converts a {@link RegistrazioneUtenteDto} to a {@link Utente} entity.
     *
     * @param dto the registration DTO containing user data
     * @return the corresponding {@link Utente} entity
     */
    public Utente toEntityFrom(RegistrazioneUtenteDto dto) {
        return modelMapper.map(dto, Utente.class);
    }

    /**
     * Maps a {@link Utente} entity to an instance of the specified destination
     * class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the {@link Utente} entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    @Override
    public <T> T map(Utente entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    /**
     * Maps an object to a {@link UtenteDto}.
     *
     * @param entity the object to map to a {@link UtenteDto}
     * @return the corresponding {@link UtenteDto}
     */
    @Override
    public UtenteDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, UtenteDto.class);
    }
}
