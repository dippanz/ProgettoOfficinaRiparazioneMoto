/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 
 */
@Entity
@Table(name = "UTENTE")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private UUID id;
	
	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
	@Column(name = "Nome", length = 256)
	private String nome;
	
	@Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
	@Column(name = "Cognome", length = 256)
	private String cognome;
	
	@Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
	@Column(name = "Telefono", length = 256)
	private String telefono;
	
	@NotNull(message = "Email obbligatoria")
	@Email
	@Column(name = "Email", nullable = false, unique = true, length = 256)
	private String email;
	
	@Size(min = 4, max = 50, message = "Il userName non può superare i 50 caratteri")
	@Column(name = "UserName", unique = true, length = 256)
	private String userName;
	
	@NotBlank(message = "HashPassword obbligatoria")
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,64}$", 
			message = "La password non rispetta i criteri richiesti"
	)
	@Column(name = "HashPassword")
	private String hashPassword;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_ruolo", nullable = false)
	private Ruolo ruolo;
	
	@OneToMany(mappedBy = "utenteReg", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Cliente> clienti;
	
	@OneToMany(mappedBy = "utenteReg", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Moto> listaMoto;
	
	@OneToMany(mappedBy = "utenteMec", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = BCrypt.hashpw(hashPassword, BCrypt.gensalt());
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public List<Cliente> getClienti() {
		return clienti;
	}
	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}
	public List<Moto> getListaMoto() {
		return listaMoto;
	}
	public void setListaMoto(List<Moto> listaMoto) {
		this.listaMoto = listaMoto;
	}
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
}
