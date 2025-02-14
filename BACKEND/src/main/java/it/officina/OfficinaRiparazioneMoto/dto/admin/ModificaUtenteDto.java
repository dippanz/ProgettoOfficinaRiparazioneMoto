package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.UUID;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for modifying user details in the admin module.
 * <p>
 * This DTO is used to update a user's personal information including name,
 * surname,
 * email, telephone, and username. Validation annotations ensure that the data
 * adheres
 * to the required format and length restrictions.
 * </p>
 */
public class ModificaUtenteDto {

    private UUID id;

    @Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
    private String nome;

    @Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
    private String cognome;

    @Email(message = "Formato dell'email non valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
    private String telefono;

    @Size(min = 4, max = 50, message = "L'username non può superare i 50 caratteri")
    private String username;

    /**
     * Default constructor.
     */
    public ModificaUtenteDto() {
    }

    /**
     * Constructs a {@code ModificaUtenteDto} with the specified user ID.
     *
     * @param id the unique identifier of the user to be modified
     */
    public ModificaUtenteDto(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user ID as a {@code UUID}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the user ID to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name as a {@code String}
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the user's first name.
     *
     * @param nome the first name to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the user's surname.
     *
     * @return the surname as a {@code String}
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the user's surname.
     *
     * @param cognome the surname to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address as a {@code String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's telephone number.
     *
     * @return the telephone number as a {@code String}
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the user's telephone number.
     *
     * @param telefono the telephone number to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the user's username.
     *
     * @return the username as a {@code String}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
