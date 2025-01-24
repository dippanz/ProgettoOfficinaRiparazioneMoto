package it.officina.OfficinaRiparazioneMoto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{
    
    @Autowired
    UtenteDao utenteDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Utente registraUtente(UtenteDto utenteDto) {
        if (utenteDao.existsByEmail(utenteDto.getEmail())) {
            //LANCIARE ECCEZZIONE PERSONALIZZATA PER ERRORE PERSONALIZZATO
            throw new IllegalArgumentException("Username già esistente");

        }

        //fare map diretto con un mapper
        Utente utente = new Utente(null, null, null, null, null, null);

        // Hash della password
        utente.setHashPassword(passwordEncoder.encode(utenteDto.getHashPassword()));
        return utenteDao.save(utente);
    }


}
