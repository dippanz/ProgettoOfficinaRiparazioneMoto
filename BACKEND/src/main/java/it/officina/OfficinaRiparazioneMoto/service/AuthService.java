/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;

/**
 * 
 */
public interface AuthService extends UserDetailsService{

    UtenteDto getUtenteDtoAutenticato();
}
