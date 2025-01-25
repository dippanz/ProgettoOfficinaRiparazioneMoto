package it.officina.OfficinaRiparazioneMoto.service;

import it.officina.OfficinaRiparazioneMoto.dto.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * 
 */
public interface UtenteService {

    Utente registraUtente(RegistrazioneUtenteDto utenteDto);
}
