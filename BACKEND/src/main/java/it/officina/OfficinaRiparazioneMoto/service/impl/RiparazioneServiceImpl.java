package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RiparazioneDao;
import it.officina.OfficinaRiparazioneMoto.dao.StatoRiparazioneDao;
import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoClienteDto;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.mapper.UtenteMapper;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.EnumStatoRiparazione;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class RiparazioneServiceImpl implements RiparazioneService {

    @Autowired
    private RiparazioneDao riparazioneDao;
    @Autowired
    private StatoRiparazioneDao statoRiparazioneDao;

    @Autowired
    private MotoService motoService;

    @Autowired
    private RiparazioneMapper mapper;
    @Autowired
    private UtenteMapper utenteMapper;

    @Override
    public List<RiparazioneMotoDto> getListaRiparazioneMotoDto(String codiceServizio, String targa)
            throws BadRequestException {

        List<RiparazioneMotoDto> response = new ArrayList<>();

        if (codiceServizio != null) {
            Riparazione riparazione = riparazioneDao.findByCodiceServizioAndTarga(codiceServizio, targa)
                    .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_COD_SERVIZIO));

            response.add(mapper.entityToRiparazioneMotoDto(riparazione));
        } else {
            List<Riparazione> riparazioni = riparazioneDao.findRiparazioniByTarga(targa);

            if (riparazioni.isEmpty()) {
                throw new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_TARGA);
            }

            response.addAll(mapper.entityToListRiparazioneMotoDto(riparazioni));
        }

        return response;
    }

    @Override
    public RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione) {

        riparazione.setCodiceServizio(generaCodiceServizio());

        MotoDto moto = motoService.getMotoDtoById(riparazione.getIdMoto());

        StatoRiparazione stato = statoRiparazioneDao.findById(EnumStatoRiparazione.REGISTRATO.getValue())
                .orElseThrow(() -> new BadRequestException(ErrorManager.STATO_RIPARAZIONE_NON_TROVATO));

        Riparazione riparazioneDb = mapper.toEntity(riparazione, moto, stato);
        return mapper.toDto(riparazioneDao.save(riparazioneDb));
    }

    private String generaCodiceServizio() {
        int LUNGHEZZA_CODICE = 4;
        String CARATTERI = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();

        // Genera una stringa casuale di lettere maiuscole e numeri
        StringBuilder codice = new StringBuilder(RandomStringUtils.random(LUNGHEZZA_CODICE, CARATTERI));

        // Prende gli ultimi 3 caratteri del timestamp attuale
        String timestamp = String.valueOf(System.currentTimeMillis());
        String timestampShort = timestamp.substring(timestamp.length() - 3);

        // Inserisco numeri timestamp in posizioni casuali
        for (char carattere : timestampShort.toCharArray()) {
            int posizioneCasuale = random.nextInt(codice.length() + 1); // +1 per permettere inserimento alla fine
            codice = codice.insert(posizioneCasuale, carattere);
        }

        return codice.toString();
    }

    @Override
    public List<RiparazioneMotoClienteDto> getListaRiparazioniMotoClienteDto(UUID idUtenteReg, UUID idUtenteMec,
            int... stati) {
        if (idUtenteReg == null && idUtenteMec == null && stati.length == 0) {
            throw new IllegalArgumentException("Entrambi gli id non posso essere null e stati vuoto");
        }

        List<Riparazione> listaRiparazioni;
        if (idUtenteMec != null) {
            // Se c'è un meccanico, filtra per meccanico e stati (se presenti)
            listaRiparazioni = stati.length > 0
                    ? riparazioneDao.findAllByIdUtenteMecAndStati(idUtenteMec, stati)
                    : riparazioneDao.findAllByUtenteMecId(idUtenteMec);
        } else if (idUtenteReg != null) {
            // Se c'è un utente registrato, filtra per utente e stati (se presenti)
            listaRiparazioni = stati.length > 0
                    ? riparazioneDao.findAllByIdUtenteRegAndStati(idUtenteReg, stati)
                    : riparazioneDao.findAllByIdUtenteReg(idUtenteReg);
        } else {
            // Se non ci sono ID utente, filtra solo per stati
            listaRiparazioni = riparazioneDao.findAllByIdStati(stati);
        }

        return mapper.entityToListRiparazioneMotoClienteDto(listaRiparazioni);
    }

    @Override
    public void aggiornaStatoRiparazione(UUID idRiparazione, UtenteDto utenteMec) throws BadRequestException {
        Riparazione riparazioneDaAggiornare = riparazioneDao.findById(idRiparazione)
                .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA));

        if (EnumStatoRiparazione.fromValore(riparazioneDaAggiornare.getStato().getId()) == EnumStatoRiparazione.COMPLETATA) {
            throw new BadRequestException(ErrorManager.RIPARAZIONE_GIA_COMPLETA);
        }

        if (EnumStatoRiparazione.fromValore(riparazioneDaAggiornare.getStato().getId()) == EnumStatoRiparazione.IN_LAVORAZIONE && utenteMec != null) {
           throw new IllegalArgumentException("Non è possibile settare un utente meccanico se si avanza verso completata");
        }

        StatoRiparazione stato = statoRiparazioneDao
                .findById(EnumStatoRiparazione.getNextStato(riparazioneDaAggiornare.getStato().getId()).getValue())
                .orElseThrow(() -> new BadRequestException(ErrorManager.STATO_RIPARAZIONE_NON_TROVATO));

        riparazioneDaAggiornare.setStato(stato);
        if (utenteMec != null && utenteMec.getId() != null) {
            // se presente inserisco nella riparazione anche l'utente meccanico che ha preso
            // in carico la riparazione
            riparazioneDaAggiornare.setUtenteMec(utenteMapper.toEntity(utenteMec));
        }
        riparazioneDao.save(riparazioneDaAggiornare);
    }

    @Override
    public RiparazioneMotoClienteDto getRiparazioneMotoClienteDto(UUID idRiparazione) throws BadRequestException {
        return mapper.entityToListRiparazioneMotoClienteDto(riparazioneDao.findById(idRiparazione)
                .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA)));
    }

    @Override
    public RiparazioneDto getRiparazioneDto(UUID idRiparazione) {
        return mapper.toDto(riparazioneDao.findById(idRiparazione)
                .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA)));
    }
}
