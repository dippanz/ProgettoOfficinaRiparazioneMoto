package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.model.Cliente;

/**
 * Mapper component for converting between {@link Cliente} entities and
 * {@link ClienteDto} Data Transfer Objects.
 * <p>
 * This class leverages the {@link ModelMapper} library to perform automatic
 * mapping between the entity
 * and DTO. Custom mapping configurations can be added if needed.
 * </p>
 */
@Component
public class ClienteMapper implements BaseMapper<Cliente, ClienteDto> {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a {@link Cliente} entity to a {@link ClienteDto}.
     *
     * @param entity the {@link Cliente} entity to be converted
     * @return the corresponding {@link ClienteDto}
     */
    @Override
    public ClienteDto toDto(Cliente entity) {
        return modelMapper.map(entity, ClienteDto.class);
    }

    /**
     * Converts a {@link ClienteDto} to a {@link Cliente} entity.
     *
     * @param dto the {@link ClienteDto} to be converted
     * @return the corresponding {@link Cliente} entity
     */
    @Override
    public Cliente toEntity(ClienteDto dto) {
        return modelMapper.map(dto, Cliente.class);
    }

    /**
     * Maps a {@link Cliente} entity to an instance of the specified destination
     * class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the {@link Cliente} entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    @Override
    public <T> T map(Cliente entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    /**
     * Maps an entity to a {@link ClienteDto}.
     *
     * @param entity the object to map to a {@link ClienteDto}
     * @return the corresponding {@link ClienteDto}
     */
    @Override
    public ClienteDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, ClienteDto.class);
    }
}
