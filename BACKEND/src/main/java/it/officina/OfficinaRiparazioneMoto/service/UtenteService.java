package it.officina.OfficinaRiparazioneMoto.service;

import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * 
 */
public interface UtenteService {

    UtenteDto registraUtente(RegistrazioneUtenteDto utenteDto);
}
