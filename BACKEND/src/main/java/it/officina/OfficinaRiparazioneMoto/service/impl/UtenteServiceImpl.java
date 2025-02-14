package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.RuoloDao;
import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.ModificaUtenteDto;
import it.officina.OfficinaRiparazioneMoto.dto.admin.RegistrazioneUtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.mapper.UtenteMapper;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.UtenteService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

/**
 * Implementation of the {@link UtenteService} interface.
 * <p>
 * Provides methods for registering users, retrieving user roles and lists, and
 * modifying user details.
 * </p>
 */
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

    @Override
    public void modificaUtente(ModificaUtenteDto request) throws BadRequestException {
        Utente utente = utenteDao.findById(request.getId())
                .orElseThrow(() -> new BadRequestException(ErrorManager.UTENTE_NON_TROVATO));

        if (request.getNome() != null) {
            utente.setNome(request.getNome());
        }
        if (request.getCognome() != null) {
            utente.setCognome(request.getCognome());
        }
        if (request.getEmail() != null) {
            Optional<Utente> altroUtente = utenteDao.findByEmail(request.getEmail());
            if (altroUtente.isPresent() && !altroUtente.get().getId().equals(request.getId())) {
                throw new BadRequestException(ErrorManager.EMAIL_ALREADY_EXISTS);
            }
            utente.setEmail(request.getEmail());
        }
        if (request.getTelefono() != null) {
            utente.setTelefono(request.getTelefono());
        }
        if (request.getUsername() != null) {
            Optional<Utente> altroUtente = utenteDao.findByUsername(request.getUsername());
            if (altroUtente.isPresent() && !altroUtente.get().getId().equals(request.getId())) {
                throw new BadRequestException(ErrorManager.USERNAME_ALREADY_EXISTS);
            }
            utente.setUsername(request.getUsername());
        }
        utenteDao.save(utente);
    }
}
