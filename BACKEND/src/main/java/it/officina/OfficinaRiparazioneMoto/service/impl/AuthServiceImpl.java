package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;
import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;
import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String identify) throws UsernameNotFoundException {

        Optional<Utente> utenteOp = utenteDao.findByEmailWithRoles(identify);

        if (!utenteOp.isPresent()) {
            utenteOp = utenteDao.findByUsernameWithRoles(identify);

            if (!utenteOp.isPresent()) {
                throw new UsernameNotFoundException("Nessun utente trovato con questo identificativo: " + identify);
            }
        }

        return new User(
                utenteOp.get().getEmail(),
                utenteOp.get().getHashPassword(),
                utenteOp.get().getRuoli().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome()))
                        .toList());
    }

    @Override
    public UtenteDto getUtenteDtoAutenticato() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new BadRequestException(ErrorManager.CLIENTE_EMAIL_ESISTENTE);
        }

        String emailUtente = ((User) authentication.getPrincipal()).getUsername();

        Utente utente = utenteDao.findByEmail(emailUtente)
                .orElseThrow(() -> new BadRequestException("Utente non trovato"));

        return modelMapper.map(utente, UtenteDto.class);
    }

    // @Override
    // public Utente getUtenteAutenticato() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //     if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
    //         throw new BadRequestException(ErrorManager.UTENTE_NON_LOGGATO);
    //     }

    //     String emailUtente = ((User) authentication.getPrincipal()).getUsername();

    //     return utenteDao.findByEmail(emailUtente)
    //             .orElseThrow(() -> new BadRequestException("Utente non trovato"));
    // }

}
