package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for modifying client details.
 * <p>
 * This DTO is used in the admin module to update a client's personal
 * information,
 * including their name, surname, email, and telephone. Validation annotations
 * ensure
 * that the provided data conforms to the required formats and length
 * restrictions.
 * </p>
 */
public class ModificaClienteDto {

    private UUID id;

	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
    private String nome;

    @Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
    private String cognome;

    @Email(message = "Formato dell'email non valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
    private String telefono;

    /**
     * Default constructor.
     */
    public ModificaClienteDto() {
    }

    /**
     * Constructs a {@code ModificaClienteDto} with the specified client identifier.
     *
     * @param id the unique identifier of the client to be modified
     */
    public ModificaClienteDto(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the client.
     *
     * @return the client's UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id the client's UUID to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the first name of the client.
     *
     * @return the client's first name as a String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the first name of the client.
     *
     * @param nome the client's first name to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the surname of the client.
     *
     * @return the client's surname as a String
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the surname of the client.
     *
     * @param cognome the client's surname to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets the client's telephone number.
     *
     * @return the telephone number as a String
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the client's telephone number.
     *
     * @param telefono the telephone number to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the client's email address.
     *
     * @return the email address as a String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
