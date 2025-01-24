package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.officina.OfficinaRiparazioneMoto.dao.UtenteDao;
import it.officina.OfficinaRiparazioneMoto.model.Utente;
import it.officina.OfficinaRiparazioneMoto.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UtenteDao utenteDao;

    @Override
    public UserDetails loadUserByUsername(String identify) throws UsernameNotFoundException {
       
        Optional<Utente> utenteOp = utenteDao.findByEmailWithRoles(identify);

        if(!utenteOp.isPresent()){
            utenteOp = utenteDao.findByUsernameWithRoles(identify);

            if(!utenteOp.isPresent()){
                throw new UsernameNotFoundException("Nessun utente trovato con questo identificativo: " + identify);
            }
        }

        return new User(
            utenteOp.get().getEmail(),
            utenteOp.get().getHashPassword(),
            utenteOp.get().getRuoli().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome()))
                .toList()
        );
    }

}
