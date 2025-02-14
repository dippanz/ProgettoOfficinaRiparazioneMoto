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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity representing a client.
 * <p>
 * This entity stores client information including name, surname, telephone, and
 * email.
 * The {@code nome} and {@code cognome} fields are converted to uppercase using
 * {@link UppercaseConverter},
 * while the {@code email} field is converted to lowercase using
 * {@link LowercaseConverter}.
 * </p>
 */
@Entity
@Table(name = "\"CLIENTE\"")
public class Cliente {

	/**
	 * The unique identifier for the client.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * The client's first name.
	 */
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String nome;

	/**
	 * The client's last name.
	 */
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String cognome;

	/**
	 * The client's telephone number.
	 */
	@Column(length = 256)
	private String telefono;

	/**
	 * The client's email address.
	 * <p>
	 * This field is mandatory and unique. It is converted to lowercase using
	 * {@link LowercaseConverter}.
	 * </p>
	 */
	@NotBlank(message = "Email obbligatoria")
	@Column(length = 256, unique = true, nullable = false)
	@Convert(converter = LowercaseConverter.class)
	private String email;

	/**
	 * The list of motorcycles associated with the client.
	 */
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Moto> listaMoto;

	/**
	 * Gets the client's unique identifier.
	 *
	 * @return the client's UUID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the client's unique identifier.
	 *
	 * @param id the client's UUID to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the client's first name.
	 *
	 * @return the client's first name
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the client's first name.
	 *
	 * @param nome the client's first name to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the client's last name.
	 *
	 * @return the client's last name
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Sets the client's last name.
	 *
	 * @param cognome the client's last name to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Gets the client's telephone number.
	 *
	 * @return the client's telephone number
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the client's telephone number.
	 *
	 * @param telefono the client's telephone number to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the client's email address.
	 *
	 * @return the client's email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the client's email address.
	 *
	 * @param email the client's email address to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the list of motorcycles associated with the client.
	 *
	 * @return a list of motorcycles
	 */
	public List<Moto> getListaMoto() {
		return listaMoto;
	}

	/**
	 * Sets the list of motorcycles associated with the client.
	 *
	 * @param listaMoto the list of motorcycles to set
	 */
	public void setListaMoto(List<Moto> listaMoto) {
		this.listaMoto = listaMoto;
	}
}
