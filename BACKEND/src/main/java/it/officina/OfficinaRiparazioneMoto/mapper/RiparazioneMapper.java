package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;
import jakarta.annotation.PostConstruct;

/**
 * Mapper component for converting between {@link Riparazione} entities and
 * various
 * Data Transfer Objects (DTOs) related to repair information.
 * <p>
 * This mapper supports conversion between:
 * <ul>
 * <li>{@link Riparazione} and {@link RiparazioneDto}</li>
 * <li>{@link Riparazione} and {@link RiparazioneMotoDto} (includes specific
 * fields related to the motorcycle)</li>
 * <li>{@link Riparazione} and {@link RiparazioneMotoClienteDto} (includes
 * detailed motorcycle and client information)</li>
 * </ul>
 * <p>
 * It also provides helper methods to convert lists of {@link Riparazione}
 * entities to collections of DTOs.
 * Custom mappings are configured in the {@code configureMappings()} method.
 * </p>
 */
@Component
public class RiparazioneMapper implements BaseMapper<Riparazione, RiparazioneDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MotoMapper motoMapper;

    @Autowired
    private UtenteMapper utenteMapper;

    /**
     * Configures custom mappings for conversion between {@link Riparazione} and its
     * DTOs.
     * <p>
     * The custom mappings include:
     * <ul>
     * <li>Mapping the motorcycle ID from the associated {@code Moto} entity to
     * {@link RiparazioneDto#setIdMoto()}</li>
     * <li>Mapping the mechanic user ID from the associated {@code Utente} entity to
     * {@link RiparazioneDto#setIdUtenteMec()}</li>
     * <li>Mapping the repair state value from the {@link StatoRiparazione} to
     * {@link RiparazioneDto#setStatoRiparazione()}</li>
     * <li>Mapping additional fields for {@link RiparazioneMotoDto} and
     * {@link RiparazioneMotoClienteDto}</li>
     * </ul>
     * </p>
     */
    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Riparazione e
        // RiparazioneDTO
        modelMapper.typeMap(Riparazione.class, RiparazioneDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getMoto().getId(), RiparazioneDto::setIdMoto);
            mapper.map(src -> src.getUtenteMec().getId(), RiparazioneDto::setIdUtenteMec);
            mapper.map(src -> src.getStato().getStato(), RiparazioneDto::setStatoRiparazione);
        });

        // Configurazione personalizzata per Riparazione -> RiparazioneMotoDto
        modelMapper.typeMap(Riparazione.class, RiparazioneMotoDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getStato().getStato(), RiparazioneMotoDto::setStatoRiparazione);
            mapper.map(src -> src.getUtenteMec().getId(), RiparazioneMotoDto::setIdUtenteMec);
            mapper.map(src -> src.getMoto().getModello(), RiparazioneMotoDto::setModello);
            mapper.map(src -> src.getMoto().getTarga(), RiparazioneMotoDto::setTarga);
        });

        // Configurazione personalizzata per Riparazione -> RiparazioneMotoClienteDto
        modelMapper.typeMap(Riparazione.class, RiparazioneMotoClienteDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getUtenteMec().getId(), RiparazioneMotoClienteDto::setIdUtenteMec);
            mapper.map(src -> src.getStato().getStato(), RiparazioneMotoClienteDto::setStatoRiparazione);
            mapper.map(src -> src.getMoto().getModello(), RiparazioneMotoClienteDto::setModello);
            mapper.map(src -> src.getMoto().getTarga(), RiparazioneMotoClienteDto::setTarga);
            mapper.map(src -> src.getMoto().getCliente().getNome(), RiparazioneMotoClienteDto::setNome);
            mapper.map(src -> src.getMoto().getCliente().getCognome(), RiparazioneMotoClienteDto::setCognome);
            mapper.map(src -> src.getMoto().getCliente().getTelefono(), RiparazioneMotoClienteDto::setTelefono);
            mapper.map(src -> src.getMoto().getCliente().getEmail(), RiparazioneMotoClienteDto::setEmail);
        });
    }

    /**
     * Converts a {@link Riparazione} entity to a {@link RiparazioneDto}.
     *
     * @param entity the {@link Riparazione} entity to convert
     * @return the corresponding {@link RiparazioneDto}
     */
    @Override
    public RiparazioneDto toDto(Riparazione entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }

    /**
     * Converts a {@link RiparazioneDto} to a {@link Riparazione} entity.
     *
     * @param dto the {@link RiparazioneDto} to convert
     * @return the corresponding {@link Riparazione} entity
     */
    @Override
    public Riparazione toEntity(RiparazioneDto dto) {
        return modelMapper.map(dto, Riparazione.class);
    }

    /**
     * Converts a {@link RiparazioneDto} to a {@link Riparazione} entity and sets
     * the associated {@link MotoDto}.
     *
     * @param dto  the {@link RiparazioneDto} to convert
     * @param moto the {@link MotoDto} representing the motorcycle
     * @return the corresponding {@link Riparazione} entity with the motorcycle set
     */
    public Riparazione toEntity(RiparazioneDto dto, MotoDto moto) {
        return toEntity(dto, moto, null, null);
    }

    /**
     * Converts a {@link RiparazioneDto} to a {@link Riparazione} entity and sets
     * the repair state.
     *
     * @param dto              the {@link RiparazioneDto} to convert
     * @param statoRiparazione the {@link StatoRiparazione} representing the repair
     *                         state
     * @return the corresponding {@link Riparazione} entity with the state set
     */
    public Riparazione toEntity(RiparazioneDto dto, StatoRiparazione statoRiparazione) {
        return toEntity(dto, null, statoRiparazione, null);
    }

    /**
     * Converts a {@link RiparazioneDto} to a {@link Riparazione} entity and sets
     * the associated
     * {@link MotoDto}, {@link StatoRiparazione}, and {@link UtenteDto} for the
     * registering user.
     *
     * @param dto              the {@link RiparazioneDto} to convert
     * @param moto             the {@link MotoDto} representing the motorcycle (may
     *                         be null)
     * @param statoRiparazione the {@link StatoRiparazione} representing the repair
     *                         state (may be null)
     * @param utenteReg        the {@link UtenteDto} representing the registering
     *                         user (may be null)
     * @return the corresponding {@link Riparazione} entity with the provided
     *         associations set
     */
    public Riparazione toEntity(RiparazioneDto dto, MotoDto moto, StatoRiparazione statoRiparazione,
            UtenteDto utenteReg) {
        Riparazione riparazione = toEntity(dto);
        if (moto != null) {
            riparazione.setMoto(motoMapper.toEntity(moto));
        }
        if (statoRiparazione != null) {
            riparazione.setStato(statoRiparazione);
        }
        if (utenteReg != null) {
            riparazione.setUtenteReg(utenteMapper.toEntity(utenteReg));
        }
        return riparazione;
    }

    /**
     * Maps a {@link Riparazione} entity to an instance of the specified destination
     * class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the {@link Riparazione} entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    @Override
    public <T> T map(Riparazione entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    /**
     * Maps an object to a {@link RiparazioneDto}.
     *
     * @param entity the object to map to a {@link RiparazioneDto}
     * @return the corresponding {@link RiparazioneDto}
     */
    @Override
    public RiparazioneDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }

    /**
     * Converts a list of {@link Riparazione} entities to a list of
     * {@link RiparazioneMotoClienteDto} objects.
     *
     * @param listaRiparazioni the list of {@link Riparazione} entities to convert
     * @return a list of {@link RiparazioneMotoClienteDto} objects containing
     *         detailed motorcycle and client information
     */
    public List<RiparazioneMotoClienteDto> entityToListRiparazioneMotoClienteDto(List<Riparazione> listaRiparazioni) {
        List<RiparazioneMotoClienteDto> listaDto = new ArrayList<>();
        for (Riparazione rip : listaRiparazioni) {
            listaDto.add(modelMapper.map(rip, RiparazioneMotoClienteDto.class));
        }
        return listaDto;
    }

    /**
     * Converts a single {@link Riparazione} entity to a
     * {@link RiparazioneMotoClienteDto}.
     *
     * @param rip the {@link Riparazione} entity to convert
     * @return the corresponding {@link RiparazioneMotoClienteDto}
     */
    public RiparazioneMotoClienteDto entityToListRiparazioneMotoClienteDto(Riparazione rip) {
        return modelMapper.map(rip, RiparazioneMotoClienteDto.class);
    }

    /**
     * Converts a {@link Riparazione} entity to a {@link RiparazioneMotoDto}.
     *
     * @param riparazione the {@link Riparazione} entity to convert
     * @return the corresponding {@link RiparazioneMotoDto}
     */
    public RiparazioneMotoDto entityToRiparazioneMotoDto(Riparazione riparazione) {
        return modelMapper.map(riparazione, RiparazioneMotoDto.class);
    }

    /**
     * Converts a list of {@link Riparazione} entities to a collection of
     * {@link RiparazioneMotoDto} objects.
     *
     * @param riparazioni the list of {@link Riparazione} entities to convert
     * @return a collection of {@link RiparazioneMotoDto} objects
     */
    public Collection<RiparazioneMotoDto> entityToListRiparazioneMotoDto(List<Riparazione> riparazioni) {
        List<RiparazioneMotoDto> dto = new ArrayList<>();
        for (Riparazione riparazione : riparazioni) {
            dto.add(entityToRiparazioneMotoDto(riparazione));
        }
        return dto;
    }
}
