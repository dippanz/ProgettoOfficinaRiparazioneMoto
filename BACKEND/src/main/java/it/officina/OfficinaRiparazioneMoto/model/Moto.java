package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity representing a motorcycle.
 * <p>
 * This entity stores information about a motorcycle, including its model,
 * license plate (targa), associated client, and repair records.
 * The license plate is converted to uppercase using {@link UppercaseConverter}.
 * </p>
 */
@Entity
@Table(name = "\"MOTO\"")
public class Moto {

	/**
	 * The unique identifier for the motorcycle.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * The model of the motorcycle.
	 */
	@Column(length = 256)
	private String modello;

	/**
	 * The license plate of the motorcycle.
	 * <p>
	 * This field is mandatory, unique, and is converted to uppercase using
	 * {@link UppercaseConverter}.
	 * </p>
	 */
	@NotBlank(message = "Targa obbligatoria")
	@Column(length = 20, unique = true, nullable = false)
	@Convert(converter = UppercaseConverter.class)
	private String targa;

	/**
	 * The client associated with the motorcycle.
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	/**
	 * The list of repairs associated with the motorcycle.
	 */
	@OneToMany(mappedBy = "moto", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;

	/**
	 * Gets the unique identifier of the motorcycle.
	 *
	 * @return the UUID of the motorcycle
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the motorcycle.
	 *
	 * @param id the UUID to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the model of the motorcycle.
	 *
	 * @return the motorcycle model
	 */
	public String getModello() {
		return modello;
	}

	/**
	 * Sets the model of the motorcycle.
	 *
	 * @param modello the motorcycle model to set
	 */
	public void setModello(String modello) {
		this.modello = modello;
	}

	/**
	 * Gets the license plate of the motorcycle.
	 *
	 * @return the license plate
	 */
	public String getTarga() {
		return targa;
	}

	/**
	 * Sets the license plate of the motorcycle.
	 *
	 * @param targa the license plate to set
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}

	/**
	 * Gets the client associated with the motorcycle.
	 *
	 * @return the associated {@link Cliente}
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the client associated with the motorcycle.
	 *
	 * @param cliente the {@link Cliente} to associate
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the list of repairs associated with the motorcycle.
	 *
	 * @return a list of {@link Riparazione} entities
	 */
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}

	/**
	 * Sets the list of repairs associated with the motorcycle.
	 *
	 * @param riparazioni a list of {@link Riparazione} entities to set
	 */
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
}
