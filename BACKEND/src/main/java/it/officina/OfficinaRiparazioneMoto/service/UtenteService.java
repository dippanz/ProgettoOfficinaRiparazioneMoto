package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;

/**
 * 
 */
public interface UtenteService {

    UtenteDto registraUtente(RegistrazioneUtenteDto utenteDto);
    List<String> getRuoliUtente();
}
