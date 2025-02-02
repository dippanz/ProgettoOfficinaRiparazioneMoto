package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RuoloDao;
import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Ruolo;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import it.officina.OfficinaRiparazioneMoto.shared.Constants.ErrorManager;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UtenteDao utenteDao;

    @Autowired
    RuoloDao ruoloDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UtenteDto registraUtente(RegistrazioneUtenteDto utenteDto) throws BadRequestException {
        if (utenteDao.existsByEmail(utenteDto.getEmail())) {
            throw new BadRequestException(ErrorManager.EMAIL_ALREADY_EXISTS);
        }

        if (utenteDto.getUsername() != null && utenteDao.existsByUsername(utenteDto.getUsername())) {
            throw new BadRequestException(ErrorManager.USERNAME_ALREADY_EXISTS);
        }

        // Mappo l'utenteDto in un oggetto Utente
        Utente utente = modelMapper.map(utenteDto, Utente.class);
        utente.setRuoli(ruoloDao.findByNomeIn(utenteDto.getRuoli()));
        
        // Hash della password
        utente.setHashPassword(passwordEncoder.encode(utenteDto.getHashPassword()));
        // ritorno un oggetto UtenteDto per non esporre troppi dati
        return modelMapper.map(utenteDao.save(utente), UtenteDto.class);
    }

    @Override
    public List<String> getRuoliUtente() {

        List<String> response = new ArrayList<>();

        ruoloDao.findAll().forEach(ruolo -> {
            if (!ruolo.getNome().equals("ADMIN")) {
                response.add(ruolo.getNome());
            }
        });

        return response;
    }
}
