package it.officina.OfficinaRiparazioneMoto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.ClienteVeicoloDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.DettaglioAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.accettazione.RiparazioneModuloAccettazioneDto;
import it.officina.OfficinaRiparazioneMoto.mapper.ClienteMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.MotoMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.service.AccettazioneService;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
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
    private ClienteMapper clienteMapper;
    @Autowired
    private MotoMapper motoMapper;
    @Autowired
    private RiparazioneMapper riparazioneMapper;

    @Override
    @Transactional // aggiunto per rendere le operazioni atomiche, in caso una non vada a buon fine viene effettuo rollback
    public RiparazioneModuloAccettazioneDto salvaAccettazione(ClienteVeicoloDto request) {

        // CASO IN CUI CLIENTE NON ESISTA

        // salvo il cliente (dovrebbe essere opzionale se gia presente)
        ClienteDto cliente = clienteService.salvaCliente(clienteMapper.mapEntityToDto(request));

        // salvo il veicolo (dovrebbe essere opzionale se gia presente)
        MotoDto moto = motoMapper.mapEntityToDto(request);
        moto.setIdCliente(cliente.getId());
        moto = motoService.salvaMoto(moto);

        // salvo la riparazione come accettata
        RiparazioneDto riparazione = riparazioneMapper.mapEntityToDto(request);
        riparazione.setIdMoto(moto.getId());
        riparazione = riparazioneService.salvaRiparazioneAccettata(riparazione);

        // CASO IN CUI CLIENTE ESISTA E MOTO NO

        // CASO IN CUI CLIENTE E MOTO ESISTONO

        // PASSO OGGETTI DIRETTAMENTE AL REDIRECT

        // quando torno alla pagina precedente inserisco i dati che ho caricato nel db
        RiparazioneModuloAccettazioneDto riparazioneAttribute = new RiparazioneModuloAccettazioneDto(
                riparazione.getId(),
                riparazione.getCodiceServizio(),
                moto.getTarga(),
                cliente.getEmail(),
                riparazione.getStatoRiparazione());

        return riparazioneAttribute;
    }

    @Override
    public DettaglioAccettazioneDto getDettaglioAccettazione(String idRiparazione) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDettaglioAccettazione'");
    }

}
