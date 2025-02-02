package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RiparazioneDao;
import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Riparazione;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;
import it.officina.OfficinaRiparazioneMoto.shared.Constants.ErrorManager;

@Service
public class RiparazioneServiceImpl implements RiparazioneService {

    @Autowired
    private RiparazioneDao riparazioneDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RiparazioneDettaglioGeneraleDto> getRiparazioneDettaglioGenerale(String codiceServizio, String targa) throws BadRequestException {

        List<RiparazioneDettaglioGeneraleDto> response = new ArrayList<>();;

        if (codiceServizio != null) {

            Riparazione riparazione = riparazioneDao.findByCodiceServizioAndTargaWithMoto(codiceServizio, targa)
                    .orElseThrow(() -> new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_COD_SERVIZIO));

            // puo essere semplificata ma per chiarezza ho diviso le due operazioni
            RiparazioneDettaglioGeneraleDto tmp = modelMapper.map(riparazione, RiparazioneDettaglioGeneraleDto.class);
            tmp.setTarga(riparazione.getMoto().getTarga());
            response.add(tmp);

        } else {
            List<Riparazione> riparazioni = riparazioneDao.findRiparazioniByTarga(targa);

            if(riparazioni.isEmpty()){
                throw new BadRequestException(ErrorManager.RIPARAZIONE_NON_TROVATA_TARGA);
            }

            riparazioni.forEach(rip -> {
                RiparazioneDettaglioGeneraleDto tmp = modelMapper.map(rip, RiparazioneDettaglioGeneraleDto.class);
                tmp.setTarga(rip.getMoto().getTarga());
                response.add(tmp);
            });
        }

        return response;
    }

}
