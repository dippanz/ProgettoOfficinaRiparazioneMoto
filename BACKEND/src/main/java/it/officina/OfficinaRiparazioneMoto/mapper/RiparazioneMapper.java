package it.officina.OfficinaRiparazioneMoto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.model.Moto;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;
import jakarta.annotation.PostConstruct;

@Component
public class RiparazioneMapper implements BaseMapper<Riparazione, RiparazioneDto> {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Riparazione e
        // RiparazioneDTO
        modelMapper.typeMap(Riparazione.class, RiparazioneDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getMoto().getId(), RiparazioneDto::setIdMoto);
            mapper.map(src -> src.getUtenteMec() != null ? src.getUtenteMec().getId() : null,
                    RiparazioneDto::setIdUtenteMec);
            mapper.map(src -> src.getStato().getStato(), RiparazioneDto::setStatoRiparazione);
        });
    }

    @Override
    public RiparazioneDto toDto(Riparazione entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }

    @Override
    public Riparazione toEntity(RiparazioneDto dto) {
        return modelMapper.map(dto, Riparazione.class);
    }

    public Riparazione toEntity(RiparazioneDto dto, Moto moto) {
        return toEntity(dto, moto, null);
    }

    public Riparazione toEntity(RiparazioneDto dto, StatoRiparazione statoRiparazione) {
        return toEntity(dto, null, statoRiparazione);
    }

    public Riparazione toEntity(RiparazioneDto dto, Moto moto, StatoRiparazione statoRiparazione) {
        Riparazione riparazione = toEntity(dto);
        if (moto != null) {
            riparazione.setMoto(moto);
        }
        if (statoRiparazione != null) {
            riparazione.setStato(statoRiparazione);
        }
        return riparazione;
    }

    @Override
    public <T> T map(Riparazione entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public RiparazioneDto mapToEntity(Object entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }
}
