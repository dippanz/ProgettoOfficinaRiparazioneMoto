package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity representing the repair status.
 * <p>
 * This entity defines the various states a repair can be in. Each state has a
 * unique identifier
 * and a unique description. It also maintains a list of repairs associated with
 * this status.
 * </p>
 */
@Entity
@Table(name = "\"STATO_RIPARAZIONE\"")
public class StatoRiparazione {

	/**
	 * The unique identifier for the repair status.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The name or description of the repair status.
	 */
	@Column(nullable = false, unique = true)
	private String stato;

	/**
	 * The list of repairs associated with this status.
	 */
	@OneToMany(mappedBy = "stato", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;

	/**
	 * Gets the unique identifier of the repair status.
	 *
	 * @return the status ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the repair status.
	 *
	 * @param id the status ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name or description of the repair status.
	 *
	 * @return the repair status
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Sets the name or description of the repair status.
	 *
	 * @param stato the repair status to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Gets the list of repairs associated with this status.
	 *
	 * @return a list of {@link Riparazione} entities
	 */
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}

	/**
	 * Sets the list of repairs associated with this status.
	 *
	 * @param riparazioni a list of {@link Riparazione} entities to set
	 */
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
}
