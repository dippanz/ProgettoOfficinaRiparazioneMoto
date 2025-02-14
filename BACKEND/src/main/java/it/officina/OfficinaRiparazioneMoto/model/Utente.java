package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.converter.LowercaseConverter;
import it.officina.OfficinaRiparazioneMoto.converter.UppercaseConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity representing a user.
 * <p>
 * This entity stores user information including name, surname, telephone,
 * email, username, and password hash.
 * The {@code nome} and {@code cognome} fields are converted to uppercase using
 * {@link UppercaseConverter},
 * while the {@code email} field is converted to lowercase using
 * {@link LowercaseConverter}.
 * <br>
 * Users are associated with one or more roles via a many-to-many relationship,
 * and can have associated repair records
 * as the registering user or as the mechanic handling repairs.
 * </p>
 */
@Entity
@Table(name = "\"UTENTE\"")
public class Utente {

	/**
	 * The unique identifier for the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * The user's first name.
	 */
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String nome;

	/**
	 * The user's last name.
	 */
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String cognome;

	/**
	 * The user's telephone number.
	 */
	@Column(length = 256)
	private String telefono;

	/**
	 * The user's email address.
	 * <p>
	 * This field is mandatory, unique, and is converted to lowercase.
	 * </p>
	 */
	@NotBlank(message = "Email obbligatoria")
	@Column(nullable = false, unique = true, length = 256)
	@Convert(converter = LowercaseConverter.class)
	private String email;

	/**
	 * The user's username.
	 * <p>
	 * This field is unique and converted to uppercase.
	 * </p>
	 */
	@Column(unique = true, length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String username;

	/**
	 * The hash of the user's password.
	 * <p>
	 * This field is mandatory.
	 * </p>
	 */
	@NotBlank(message = "HashPassword obbligatoria")
	@Column(name = "\"hashPassword\"", nullable = false)
	private String hashPassword;

	/**
	 * The list of roles associated with the user.
	 */
	@ManyToMany
	@JoinTable(name = "\"UTENTE_RUOLO\"", // Nome della tabella di join
			joinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_ruolo", referencedColumnName = "id"))
	private List<Ruolo> ruoli;

	/**
	 * The list of repairs registered by the user.
	 */
	@OneToMany(mappedBy = "utenteReg", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> listaRiparazioni;

	/**
	 * The list of repairs handled by the user as a mechanic.
	 */
	@OneToMany(mappedBy = "utenteMec", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;

	/**
	 * Default constructor.
	 */
	public Utente() {

	}

	/**
	 * Constructs a new {@code Utente} with the specified details.
	 *
	 * @param nome         the user's first name
	 * @param cognome      the user's last name
	 * @param telefono     the user's telephone number
	 * @param email        the user's email address
	 * @param username     the user's username
	 * @param hashpassword the hash of the user's password
	 */
	public Utente(String nome, String cognome, String telefono, String email, String username, String hashpassword) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.username = username;
		this.hashPassword = hashpassword;
	}

	/**
	 * Gets the unique identifier of the user.
	 *
	 * @return the user UUID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the user.
	 *
	 * @param id the UUID to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the user's first name.
	 *
	 * @return the first name
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
	 * Gets the user's last name.
	 *
	 * @return the last name
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Sets the user's last name.
	 *
	 * @param cognome the last name to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Gets the user's telephone number.
	 *
	 * @return the telephone number
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
	 * @return the email address
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
	 * @return the username
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
	 * Gets the hash of the user's password.
	 *
	 * @return the password hash
	 */
	public String getHashPassword() {
		return hashPassword;
	}

	/**
	 * Sets the hash of the user's password.
	 *
	 * @param hashPassword the password hash to set
	 */
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	/**
	 * Gets the list of roles associated with the user.
	 *
	 * @return a list of {@link Ruolo} objects
	 */
	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	/**
	 * Sets the list of roles associated with the user.
	 *
	 * @param ruoli a list of {@link Ruolo} objects to set
	 */
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	/**
	 * Gets the list of repairs registered by the user.
	 *
	 * @return a list of {@link Riparazione} entities
	 */
	public List<Riparazione> getListaRiparazioni() {
		return listaRiparazioni;
	}

	/**
	 * Sets the list of repairs registered by the user.
	 *
	 * @param listaRiparazioni a list of {@link Riparazione} entities to set
	 */
	public void setListaRiparazioni(List<Riparazione> listaRiparazioni) {
		this.listaRiparazioni = listaRiparazioni;
	}

	/**
	 * Gets the list of repairs handled by the user as a mechanic.
	 *
	 * @return a list of {@link Riparazione} entities
	 */
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}

	/**
	 * Sets the list of repairs handled by the user as a mechanic.
	 *
	 * @param riparazioni a list of {@link Riparazione} entities to set
	 */
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
}
