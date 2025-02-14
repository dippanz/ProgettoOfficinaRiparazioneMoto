package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.model.Moto;

/**
 * Mapper component for converting between {@link Moto} entities and their
 * corresponding DTOs.
 * <p>
 * This mapper supports conversion between {@link Moto} and {@link MotoDto} as
 * well as conversion to a
 * specialized DTO {@link MotoClienteDto} which contains selected customer
 * information.
 * Custom mappings are configured in the {@code configureMappings()} method.
 * </p>
 */
@Component
public class MotoMapper implements BaseMapper<Moto, MotoDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    /**
     * Configures custom mappings for conversion between {@link Moto} and its DTOs.
     * <p>
     * This method sets up a mapping for converting the customer ID from the
     * {@link Moto} entity
     * to the {@link MotoDto} and additional mappings to convert selected customer
     * properties
     * to the {@link MotoClienteDto}.
     * </p>
     */
    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Moto e MotoDto
        modelMapper.typeMap(Moto.class, MotoDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCliente().getId(), MotoDto::setIdCliente);
        });
        // Configurazione personalizzata per la conversione tra Moto e MotoClienteDto
        modelMapper.typeMap(Moto.class, MotoClienteDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCliente().getNome(), MotoClienteDto::setNome);
            mapper.map(src -> src.getCliente().getCognome(), MotoClienteDto::setCognome);
            mapper.map(src -> src.getCliente().getTelefono(), MotoClienteDto::setTelefono);
            mapper.map(src -> src.getCliente().getEmail(), MotoClienteDto::setEmail);
        });
    }

    /**
     * Converts a {@link Moto} entity to a {@link MotoDto}.
     *
     * @param entity the {@link Moto} entity to convert
     * @return the corresponding {@link MotoDto}
     */
    @Override
    public MotoDto toDto(Moto entity) {
        return modelMapper.map(entity, MotoDto.class);
    }

    /**
     * Converts a {@link MotoDto} to a {@link Moto} entity.
     *
     * @param dto the {@link MotoDto} to convert
     * @return the corresponding {@link Moto} entity
     */
    @Override
    public Moto toEntity(MotoDto dto) {
        return modelMapper.map(dto, Moto.class);
    }

    /**
     * Converts a {@link MotoDto} to a {@link Moto} entity and sets the associated
     * customer.
     *
     * @param dto     the {@link MotoDto} to convert
     * @param cliente the {@link ClienteDto} representing the customer
     * @return the corresponding {@link Moto} entity with the customer set, if
     *         provided
     */
    public Moto toEntity(MotoDto dto, ClienteDto cliente) {
        Moto moto = toEntity(dto);
        if (cliente != null) {
            moto.setCliente(clienteMapper.toEntity(cliente));
        }
        return moto;
    }

    /**
     * Maps a {@link Moto} entity to an instance of the specified destination class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the {@link Moto} entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    @Override
    public <T> T map(Moto entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    /**
     * Maps an object to a {@link MotoDto}.
     *
     * @param entity the object to map
     * @return the corresponding {@link MotoDto}
     */
    @Override
    public MotoDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, MotoDto.class);
    }

    /**
     * Converts a {@link Moto} entity to a {@link MotoClienteDto} containing
     * customer details.
     *
     * @param moto the {@link Moto} entity to convert
     * @return the corresponding {@link MotoClienteDto} with selected customer
     *         information
     */
    public MotoClienteDto entityToMotoClienteDto(Moto moto) {
        return modelMapper.map(moto, MotoClienteDto.class);
    }

    /**
     * Converts a list of {@link Moto} entities to a list of {@link MotoClienteDto}
     * objects.
     *
     * @param listaMoto the list of {@link Moto} entities to convert
     * @return a list of {@link MotoClienteDto} objects with selected customer
     *         information
     */
    public List<MotoClienteDto> entityToListaMotoClienteDto(List<Moto> listaMoto) {
        List<MotoClienteDto> dto = new ArrayList<>();
        for (Moto moto : listaMoto) {
            dto.add(entityToMotoClienteDto(moto));
        }
        return dto;
    }
}
