package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;
import it.officina.OfficinaRiparazioneMoto.service.AccettazioneService;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.StatoRiparazioni;
import jakarta.transaction.Transactional;

@Service
public class AccettazioneServiceImpl implements AccettazioneService {

    @Autowired
    private MotoService motoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private RiparazioneService riparazioneService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private MotoMapper motoMapper;
    @Autowired
    private RiparazioneMapper riparazioneMapper;

    @Override
    @Transactional // aggiunto per rendere le operazioni atomiche, in caso una non vada a buon fine
                   // viene effettuo rollback
    public void salvaAccettazione(ClienteVeicoloDto request) {

        MotoDto moto;
        ClienteDto cliente;

        if (request.getIdMoto() != null) {
            moto = motoService.getMotoDtoById(request.getIdMoto());
        } else {
            cliente = (request.getIdCliente() != null)
                    ? clienteService.getClienteDtoById(request.getIdCliente())
                    : clienteService.salvaCliente(clienteMapper.mapEntityToDto(request));

            // salvo la moto
            moto = motoMapper.mapEntityToDto(request);
            moto.setIdCliente(cliente.getId());
            moto = motoService.salvaMoto(moto);
        }

        // salvo la riparazione come accettata
        RiparazioneDto riparazione = riparazioneMapper.mapEntityToDto(request);
        riparazione.setIdMoto(moto.getId());
        riparazione = riparazioneService.salvaRiparazioneAccettata(riparazione);
    }

    @Override
    public DettaglioAccettazioneDto getDettaglioAccettazione(UUID idRiparazione) {

        RiparazioneMotoClienteDto riparazione = riparazioneService.getRiparazioneMotoClienteDto(idRiparazione);

        DettaglioAccettazioneDto response = new DettaglioAccettazioneDto();
        response.setId(riparazione.getId());
        response.setDescrizione(riparazione.getDescrizioneProblema());
        response.setDataInizio(riparazione.getDataInizio());
        response.setDataFine(riparazione.getDataFine());
        response.setCodiceServizio(riparazione.getCodiceServizio());
        response.setStatoRiparazione(riparazione.getStatoRiparazione());
        response.setModello(riparazione.getModello());
        response.setTarga(riparazione.getTarga());
        response.setNome(riparazione.getNome());
        response.setCognome(riparazione.getCognome());
        response.setTelefono(riparazione.getTelefono());
        response.setEmail(riparazione.getEmail());
        
        return response;
    }

    @Override
    public List<RiparazioneModuloAccettazioneDto> getListaRiparazioniModuloAccettazioneDto() {

        List<RiparazioneMotoClienteDto> listaRiparazioni = riparazioneService.getListaRiparazioniMotoClienteDto(authService.getUtenteDtoAutenticato().getId(), null);

        List<RiparazioneModuloAccettazioneDto> response = new ArrayList<>();

        for (RiparazioneMotoClienteDto rmc : listaRiparazioni) {
            RiparazioneModuloAccettazioneDto dto = new RiparazioneModuloAccettazioneDto(
                    rmc.getId(),
                    rmc.getCodiceServizio(),
                    rmc.getTarga(),
                    rmc.getEmail(),
                    rmc.getStatoRiparazione());
            response.add(dto);
        }

        return response;
    }

    @Override
    public void accettaRiparazione(UUID idRiparazione) {
        riparazioneService.aggiornaStatoRiparazione(idRiparazione, null);
    }
}
