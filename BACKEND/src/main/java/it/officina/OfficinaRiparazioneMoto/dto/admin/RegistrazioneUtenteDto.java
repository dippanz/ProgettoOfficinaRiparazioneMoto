package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for user registration in the admin module.
 * <p>
 * This DTO encapsulates the information required to register a new user,
 * including personal details,
 * contact information, username, password, and the roles to be assigned.
 * Validation annotations ensure that the provided data meets the necessary
 * format and constraints.
 * </p>
 */
public class RegistrazioneUtenteDto {

	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
	private String nome;

	@Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
	private String cognome;

	@Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{5,10}$", message = "Formato del telefono non valido")
	private String telefono;

	@Email(message = "Formato email non valido")
	private String email;

	@Size(min = 4, max = 50, message = "L'username non può superare i 50 caratteri")
	private String username;

	@NotBlank(message = "Password obbligatoria")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "La password non rispetta i criteri richiesti! Es. Password123@")
	private String hashPassword;

	@NotEmpty(message = "Deve essere assegnato almeno un ruolo")
	private List<String> ruoli;

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

	/**
	 * Gets the user's password hash.
	 *
	 * @return the password hash as a {@code String}
	 */
	public String getHashPassword() {
		return hashPassword;
	}

	/**
	 * Sets the user's password hash.
	 *
	 * @param hashPassword the password hash to set
	 */
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	/**
	 * Gets the list of roles assigned to the user.
	 *
	 * @return a list of role names as {@code List<String>}
	 */
	public List<String> getRuoli() {
		return ruoli;
	}

	/**
	 * Sets the list of roles to assign to the user.
	 *
	 * @param ruoli the list of role names to set
	 */
	public void setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
	}
}
