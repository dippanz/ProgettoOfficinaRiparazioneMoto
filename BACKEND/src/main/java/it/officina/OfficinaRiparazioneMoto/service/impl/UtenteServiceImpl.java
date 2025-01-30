package it.officina.OfficinaRiparazioneMoto.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import it.officina.OfficinaRiparazioneMoto.shared.Constants;
import it.officina.OfficinaRiparazioneMoto.shared.Constants.ErrorManager;

@Service
public class UtenteServiceImpl implements UtenteService{
    
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UtenteDao utenteDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Utente registraUtente(RegistrazioneUtenteDto utenteDto) throws BadRequestException {
        if (utenteDao.existsByEmail(utenteDto.getEmail())) {
            throw new BadRequestException(ErrorManager.EMAIL_ALREADY_EXISTS);
        }

        if (utenteDto.getUsername() != null && utenteDao.existsByUsername(utenteDto.getUsername())) {
            throw new BadRequestException(ErrorManager.USERNAME_ALREADY_EXISTS);
        }

        // Mappo l'utenteDto in un oggetto Utente
        Utente utente = modelMapper.map(utenteDto, Utente.class);

        // Hash della password
        utente.setHashPassword(passwordEncoder.encode(utenteDto.getHashPassword()));
        return utenteDao.save(utente);
    }
}
