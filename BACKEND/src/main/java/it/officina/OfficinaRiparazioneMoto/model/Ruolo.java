package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity representing a role.
 * <p>
 * This entity defines a role that can be associated with one or more users.
 * Each role has a unique name, and the many-to-many relationship with users is
 * managed via a join table.
 * </p>
 */
@Entity
@Table(name = "\"RUOLO\"")
public class Ruolo {

	/**
	 * The unique identifier for the role.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * The unique name of the role.
	 */
	@NotNull
	@Column(nullable = false, unique = true)
	private String nome;

	/**
	 * The list of users associated with this role.
	 */
	@ManyToMany
	@JoinTable(name = "\"UTENTE_RUOLO\"", // Nome della tabella di join
			joinColumns = @JoinColumn(name = "id_ruolo", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id"))
	private List<Utente> utenti;

	/**
	 * Gets the unique identifier of the role.
	 *
	 * @return the role UUID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the role.
	 *
	 * @param id the role UUID to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the name of the role.
	 *
	 * @return the role name
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the name of the role.
	 *
	 * @param nome the role name to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the list of users associated with this role.
	 *
	 * @return a list of {@link Utente} objects
	 */
	public List<Utente> getUtenti() {
		return utenti;
	}

	/**
	 * Sets the list of users associated with this role.
	 *
	 * @param utenti a list of {@link Utente} objects to set
	 */
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
}
