package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.MotoDao;
import it.officina.OfficinaRiparazioneMoto.dao.RiparazioneDao;
import it.officina.OfficinaRiparazioneMoto.dao.StatoRiparazioneDao;
import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneDto;
import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.RiparazioneMapper;
import it.officina.OfficinaRiparazioneMoto.model.Moto;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.model.StatoRiparazione;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.StatoRiparazioni;

@Service
public class RiparazioneServiceImpl implements RiparazioneService {

    @Autowired
    private RiparazioneDao riparazioneDao;

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private StatoRiparazioneDao statoRiparazioneDao;

    @Autowired
    private RiparazioneMapper mapper;

    @Override
    public List<RiparazioneDettaglioGeneraleDto> getRiparazioneDettaglioGenerale(String codiceServizio, String targa)
            throws BadRequestException {

        List<RiparazioneDettaglioGeneraleDto> response = new ArrayList<>();
        ;

        if (codiceServizio != null) {

            Riparazione riparazione = riparazioneDao.findByCodiceServizioAndTargaWithMoto(codiceServizio, targa)
                    .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_COD_SERVIZIO));

            // puo essere semplificata ma per chiarezza ho diviso le due operazioni
            RiparazioneDettaglioGeneraleDto tmp = mapper.map(riparazione, RiparazioneDettaglioGeneraleDto.class);
            tmp.setTarga(riparazione.getMoto().getTarga());
            response.add(tmp);

        } else {
            List<Riparazione> riparazioni = riparazioneDao.findRiparazioniByTarga(targa);

            if (riparazioni.isEmpty()) {
                throw new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_TARGA);
            }

            riparazioni.forEach(rip -> {
                RiparazioneDettaglioGeneraleDto tmp = mapper.map(rip, RiparazioneDettaglioGeneraleDto.class);
                tmp.setTarga(rip.getMoto().getTarga());
                response.add(tmp);
            });
        }

        return response;
    }

    @Override
    public RiparazioneDto salvaRiparazioneAccettata(RiparazioneDto riparazione) {

        riparazione.setCodiceServizio(generaCodiceServizio());

        Moto moto = motoDao.findById(riparazione.getIdMoto())
                .orElseThrow(() -> new BadRequestException(ErrorManager.MOTO_NON_TROVATA));

        StatoRiparazione stato = statoRiparazioneDao.findById(StatoRiparazioni.ACCETTATO)
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

}
