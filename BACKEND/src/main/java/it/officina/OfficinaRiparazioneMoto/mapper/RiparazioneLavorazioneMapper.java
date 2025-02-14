package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneLavorazioneDto;
import it.officina.OfficinaRiparazioneMoto.model.RiparazioneLavorazione;
import jakarta.annotation.PostConstruct;

/**
 * Mapper component for converting between {@link RiparazioneLavorazione}
 * entities and their corresponding
 * {@link RiparazioneLavorazioneDto} Data Transfer Objects.
 * <p>
 * This mapper leverages the {@link ModelMapper} library to perform automatic
 * mapping between the entity and DTO.
 * A custom mapping configuration is defined in {@code configureMappings()} to
 * map the repair ID from the associated
 * {@code Riparazione} entity.
 * </p>
 */
@Component
public class RiparazioneLavorazioneMapper implements BaseMapper<RiparazioneLavorazione, RiparazioneLavorazioneDto> {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Configures custom mappings for converting a {@link RiparazioneLavorazione}
     * entity to a
     * {@link RiparazioneLavorazioneDto}.
     * <p>
     * Specifically, maps the repair ID from the associated {@code Riparazione}
     * entity to the DTO field.
     * </p>
     */
    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(RiparazioneLavorazione.class, RiparazioneLavorazioneDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getRiparazione().getId(), RiparazioneLavorazioneDto::setIdRiparazione);
        });
    }

    /**
     * Converts a {@link RiparazioneLavorazione} entity to its corresponding
     * {@link RiparazioneLavorazioneDto}.
     *
     * @param entity the {@link RiparazioneLavorazione} entity to convert
     * @return the corresponding {@link RiparazioneLavorazioneDto}
     */
    @Override
    public RiparazioneLavorazioneDto toDto(RiparazioneLavorazione entity) {
        return modelMapper.map(entity, RiparazioneLavorazioneDto.class);
    }

    /**
     * Converts a {@link RiparazioneLavorazioneDto} to its corresponding
     * {@link RiparazioneLavorazione} entity.
     *
     * @param dto the {@link RiparazioneLavorazioneDto} to convert
     * @return the corresponding {@link RiparazioneLavorazione} entity
     */
    @Override
    public RiparazioneLavorazione toEntity(RiparazioneLavorazioneDto dto) {
        return modelMapper.map(dto, RiparazioneLavorazione.class);
    }

    /**
     * Maps a {@link RiparazioneLavorazione} entity to an instance of the specified
     * destination class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the {@link RiparazioneLavorazione} entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    @Override
    public <T> T map(RiparazioneLavorazione entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    /**
     * Maps an object to a {@link RiparazioneLavorazioneDto}.
     *
     * @param entity the object to map to a {@link RiparazioneLavorazioneDto}
     * @return the corresponding {@link RiparazioneLavorazioneDto}
     */
    @Override
    public RiparazioneLavorazioneDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, RiparazioneLavorazioneDto.class);
    }
}
