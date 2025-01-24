package it.officina.OfficinaRiparazioneMoto.service;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;

/**
 * 
 */
public interface UtenteService {

    Utente registraUtente(UtenteDto utenteDto);
}
