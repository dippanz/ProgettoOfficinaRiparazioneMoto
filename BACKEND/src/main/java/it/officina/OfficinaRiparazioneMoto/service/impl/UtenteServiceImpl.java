package it.officina.OfficinaRiparazioneMoto.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{
    
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UtenteDao utenteDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Utente registraUtente(RegistrazioneUtenteDto utenteDto) {
        if (utenteDao.existsByEmail(utenteDto.getEmail())) {
            //LANCIARE ECCEZZIONE PERSONALIZZATA PER ERRORE PERSONALIZZATO
            throw new IllegalArgumentException("Username già esistente");
        }

        // Mappo l'utenteDto in un oggetto Utente
        Utente utente = modelMapper.map(utenteDto, Utente.class);

        // Hash della password
        utente.setHashPassword(passwordEncoder.encode(utenteDto.getHashPassword()));
        return utenteDao.save(utente);
    }
}
