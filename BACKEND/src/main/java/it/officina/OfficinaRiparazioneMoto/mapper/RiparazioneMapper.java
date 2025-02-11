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

@Component
public class RiparazioneMapper implements BaseMapper<Riparazione, RiparazioneDto> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MotoMapper motoMapper;
    @Autowired
    private UtenteMapper utenteMapper;

    @PostConstruct
    public void configureMappings() {
        // Configurazione personalizzata per la conversione tra Riparazione e
        // RiparazioneDTO
        modelMapper.typeMap(Riparazione.class, RiparazioneDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getMoto().getId(), RiparazioneDto::setIdMoto);
            mapper.map(src -> src.getUtenteMec().getId(),
                    RiparazioneDto::setIdUtenteMec);
            mapper.map(src -> src.getStato().getStato(), RiparazioneDto::setStatoRiparazione);
        });

        modelMapper.typeMap(Riparazione.class, RiparazioneMotoDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getStato().getStato(), RiparazioneMotoDto::setStatoRiparazione);
            mapper.map(src -> src.getUtenteMec().getId(), RiparazioneMotoDto::setIdUtenteMec);
            mapper.map(src -> src.getMoto().getModello(), RiparazioneMotoDto::setModello);
            mapper.map(src -> src.getMoto().getTarga(), RiparazioneMotoDto::setTarga);
        });

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

    @Override
    public RiparazioneDto toDto(Riparazione entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }

    @Override
    public Riparazione toEntity(RiparazioneDto dto) {
        return modelMapper.map(dto, Riparazione.class);
    }

    public Riparazione toEntity(RiparazioneDto dto, MotoDto moto) {
        return toEntity(dto, moto, null, null);
    }

    public Riparazione toEntity(RiparazioneDto dto, StatoRiparazione statoRiparazione) {
        return toEntity(dto, null, statoRiparazione, null);
    }

    public Riparazione toEntity(RiparazioneDto dto, MotoDto moto, StatoRiparazione statoRiparazione, UtenteDto utenteReg) {
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

    @Override
    public <T> T map(Riparazione entity, Class<T> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }

    @Override
    public RiparazioneDto mapEntityToDto(Object entity) {
        return modelMapper.map(entity, RiparazioneDto.class);
    }

    public List<RiparazioneMotoClienteDto> entityToListRiparazioneMotoClienteDto(List<Riparazione> listaRiparazioni) {
        List<RiparazioneMotoClienteDto> listaDto = new ArrayList<>();

        for (Riparazione rip : listaRiparazioni) {
            listaDto.add(modelMapper.map(rip, RiparazioneMotoClienteDto.class));
        }

        return listaDto;
    }

    public RiparazioneMotoClienteDto entityToListRiparazioneMotoClienteDto(Riparazione rip) {
        return modelMapper.map(rip, RiparazioneMotoClienteDto.class);
    }

    public RiparazioneMotoDto entityToRiparazioneMotoDto(Riparazione riparazione) {
        return modelMapper.map(riparazione, RiparazioneMotoDto.class);
    }

    public Collection<RiparazioneMotoDto> entityToListRiparazioneMotoDto(List<Riparazione> riparazioni) {
        List<RiparazioneMotoDto> dto = new ArrayList<>();
        for (Riparazione riparazione : riparazioni) {
            dto.add(entityToRiparazioneMotoDto(riparazione));
        }
        return dto;
    }
}
