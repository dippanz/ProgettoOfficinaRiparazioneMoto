package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RuoloDao;
import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.UtenteMapper;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteMapper mapper;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private RuoloDao ruoloDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UtenteDto registraUtente(RegistrazioneUtenteDto utenteDto) throws BadRequestException {
        if (utenteDao.existsByEmail(utenteDto.getEmail())) {
            throw new BadRequestException(ErrorManager.EMAIL_ALREADY_EXISTS);
        }

        if (utenteDto.getUsername() != null && utenteDao.existsByUsername(utenteDto.getUsername())) {
            throw new BadRequestException(ErrorManager.USERNAME_ALREADY_EXISTS);
        }

        // Mappo l'utenteDto in un oggetto Utente
        Utente utente = mapper.toEntityFrom(utenteDto);
        utente.setRuoli(ruoloDao.findByNomeIn(utenteDto.getRuoli()));
        
        // Hash della password
        utente.setHashPassword(passwordEncoder.encode(utenteDto.getHashPassword()));
        // ritorno un oggetto UtenteDto per non esporre troppi dati
        return mapper.toDto(utenteDao.save(utente));
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

    @Override
    public List<UtenteDto> getListaUtenteDto() {
        return mapper.toDtoList(utenteDao.findAllExceptAdmin());
    }
}
