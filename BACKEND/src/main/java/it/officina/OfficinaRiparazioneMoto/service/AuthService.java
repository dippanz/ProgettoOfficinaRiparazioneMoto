package it.officina.OfficinaRiparazioneMoto.service;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import it.officina.OfficinaRiparazioneMoto.dto.UtenteDto;

/**
 * Authentication service interface that extends Spring Security's
 * {@link UserDetailsService}.
 * <p>
 * This interface provides methods to obtain details about the currently
 * authenticated user.
 * </p>
 */
public interface AuthService extends UserDetailsService {

    /**
     * Retrieves the authenticated user's data as a {@link UtenteDto}.
     * <p>
     * The method obtains the current authentication from the
     * {@link SecurityContextHolder},
     * verifies that a user is logged in, retrieves the user by email, and maps the
     * entity
     * to a DTO.
     * </p>
     *
     * @return a {@link UtenteDto} representing the authenticated user's data
     * @throws InsufficientAuthenticationException        if no user is logged in or
     *                                                    the principal is not an
     *                                                    instance of {@link User}
     * @throws AuthenticationCredentialsNotFoundException if the user cannot be
     *                                                    found in the database
     */
    UtenteDto getUtenteDtoAutenticato()
            throws InsufficientAuthenticationException, AuthenticationCredentialsNotFoundException;
}
