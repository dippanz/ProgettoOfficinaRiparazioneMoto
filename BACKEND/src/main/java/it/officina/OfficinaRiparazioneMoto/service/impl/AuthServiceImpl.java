package it.officina.OfficinaRiparazioneMoto.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
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

/**
 * Implementation of the {@link AuthService} interface.
 * <p>
 * This service provides authentication-related operations, including loading
 * user details
 * for authentication and retrieving the currently authenticated user's data.
 * </p>
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Loads user details by username or email for authentication.
     * <p>
     * The method first attempts to find a user by email (converted to lowercase)
     * with associated roles.
     * If not found, it attempts to find a user by username (converted to
     * uppercase). If no user is found,
     * a {@link UsernameNotFoundException} is thrown.
     * </p>
     *
     * @param identify the username or email used to identify the user
     * @return a {@link UserDetails} object containing the user's authentication
     *         data
     * @throws UsernameNotFoundException if no user is found with the given
     *                                   identifier
     * @throws BadRequestException       if the provided identifier is null
     */
    @Override
    public UserDetails loadUserByUsername(String identify) throws UsernameNotFoundException, BadRequestException {
        if (identify == null) {
            throw new BadRequestException(ErrorManager.IDENTIFY_NULLO);
        }

        Optional<Utente> utenteOp = utenteDao.findByEmailWithRoles(identify.toLowerCase());

        if (!utenteOp.isPresent()) {
            utenteOp = utenteDao.findByUsernameWithRoles(identify.toUpperCase());

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
    public UtenteDto getUtenteDtoAutenticato()
            throws AuthenticationCredentialsNotFoundException, InsufficientAuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new InsufficientAuthenticationException("Utente non loggato");
        }

        String emailUtente = ((User) authentication.getPrincipal()).getUsername();

        Utente utente = utenteDao.findByEmail(emailUtente)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Utente non loggato"));

        return modelMapper.map(utente, UtenteDto.class);
    }
}
