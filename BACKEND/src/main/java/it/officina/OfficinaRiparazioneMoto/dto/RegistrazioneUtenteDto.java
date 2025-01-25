package it.officina.OfficinaRiparazioneMoto.dto;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.model.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrazioneUtenteDto {

	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
	private String nome;

	@Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
	private String cognome;

	@Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{5,10}$", message = "Formato del telefono non valido")
	private String telefono;

	@Email(message = "Formato email non valido")
	private String email;

	@Size(min = 4, max = 50, message = "Il userName non può superare i 50 caratteri")
	private String username;

	@NotBlank(message = "Password obbligatoria")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "La password non rispetta i criteri richiesti! Es. Password123@")
	private String hashPassword;

	// @NotEmpty(message = "Deve essere assegnato almeno un ruolo")
	// private List<String> ruoli;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
}
