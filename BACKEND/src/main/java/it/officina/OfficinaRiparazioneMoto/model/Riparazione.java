package it.officina.OfficinaRiparazioneMoto.model;

import java.time.LocalDateTime;
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
import jakarta.validation.constraints.Pattern;

/**
 * Entity representing a repair record.
 * <p>
 * This entity stores information about a repair performed on a motorcycle.
 * It includes details such as the problem description, service code, start and
 * end dates,
 * and associations with the motorcycle, the mechanic who handled the repair,
 * the user who registered the repair,
 * the current repair state, and any associated work items.
 * </p>
 */
@Entity
@Table(name = "\"RIPARAZIONE\"")
public class Riparazione {

	/**
	 * The unique identifier for the repair.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * The description of the problem that required repair.
	 */
	@Column(name = "descrizione_problema")
	private String descrizioneProblema;

	/**
	 * The start date and time of the repair.
	 * <p>
	 * This field is managed by the database (non-insertable/updatable).
	 * </p>
	 */
	@Column(name = "\"dataInizio\"", updatable = false, insertable = false)
	private LocalDateTime dataInizio;

	/**
	 * The end date and time of the repair.
	 */
	@Column(name = "\"dataFine\"")
	private LocalDateTime dataFine;

	/**
	 * The service code for the repair.
	 * <p>
	 * This field is mandatory, must match the specified pattern, unique, and is
	 * stored in uppercase.
	 * </p>
	 */
	@NotBlank(message = "Codice servizio obbligatorio")
	@Pattern(regexp = "^[A-Z0-9]{6,25}$", message = "Il codice non rispetta i criteri richiesti")
	@Column(name = "codice_servizio", unique = true, nullable = false)
	@Convert(converter = UppercaseConverter.class)
	private String codiceServizio;

	/**
	 * The motorcycle associated with this repair.
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_moto", nullable = false)
	private Moto moto;

	/**
	 * The mechanic (user) who is handling the repair.
	 * <p>
	 * This association is optional.
	 * </p>
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente_mec", nullable = true)
	private Utente utenteMec;

	/**
	 * The user who registered the repair.
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente_reg", nullable = false)
	private Utente utenteReg;

	/**
	 * The current state of the repair.
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stato", nullable = false)
	private StatoRiparazione stato;

	/**
	 * The list of work items (lavorazioni) associated with this repair.
	 */
	@OneToMany(mappedBy = "riparazione", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<RiparazioneLavorazione> lavorazioni;

	/**
	 * Gets the unique identifier of the repair.
	 *
	 * @return the repair UUID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the repair.
	 *
	 * @param id the repair UUID to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the problem description.
	 *
	 * @return the description of the problem
	 */
	public String getDescrizioneProblema() {
		return descrizioneProblema;
	}

	/**
	 * Sets the problem description.
	 *
	 * @param descrizioneProblema the description to set
	 */
	public void setDescrizioneProblema(String descrizioneProblema) {
		this.descrizioneProblema = descrizioneProblema;
	}

	/**
	 * Gets the start date and time of the repair.
	 *
	 * @return the start date and time
	 */
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	/**
	 * Gets the end date and time of the repair.
	 *
	 * @return the end date and time
	 */
	public LocalDateTime getDataFine() {
		return dataFine;
	}

	/**
	 * Sets the end date and time of the repair.
	 *
	 * @param dataFine the end date and time to set
	 */
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * Gets the motorcycle associated with the repair.
	 *
	 * @return the associated {@link Moto}
	 */
	public Moto getMoto() {
		return moto;
	}

	/**
	 * Sets the motorcycle associated with the repair.
	 *
	 * @param moto the {@link Moto} to associate
	 */
	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	/**
	 * Gets the mechanic (user) handling the repair.
	 *
	 * @return the mechanic as a {@link Utente}
	 */
	public Utente getUtenteMec() {
		return utenteMec;
	}

	/**
	 * Sets the mechanic (user) handling the repair.
	 *
	 * @param utenteMec the mechanic to set
	 */
	public void setUtenteMec(Utente utenteMec) {
		this.utenteMec = utenteMec;
	}

	/**
	 * Gets the current state of the repair.
	 *
	 * @return the {@link StatoRiparazione} representing the repair state
	 */
	public StatoRiparazione getStato() {
		return stato;
	}

	/**
	 * Sets the current state of the repair.
	 *
	 * @param stato the repair state to set
	 */
	public void setStato(StatoRiparazione stato) {
		this.stato = stato;
	}

	/**
	 * Gets the service code for the repair.
	 *
	 * @return the service code
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * Sets the service code for the repair.
	 *
	 * @param codiceServizio the service code to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	/**
	 * Gets the list of work items associated with the repair.
	 *
	 * @return a list of {@link RiparazioneLavorazione} entities
	 */
	public List<RiparazioneLavorazione> getLavorazioni() {
		return lavorazioni;
	}

	/**
	 * Sets the list of work items associated with the repair.
	 *
	 * @param lavorazioni a list of {@link RiparazioneLavorazione} entities to set
	 */
	public void setLavorazioni(List<RiparazioneLavorazione> lavorazioni) {
		this.lavorazioni = lavorazioni;
	}

	/**
	 * Gets the user who registered the repair.
	 *
	 * @return the registering user as a {@link Utente}
	 */
	public Utente getUtenteReg() {
		return utenteReg;
	}

	/**
	 * Sets the user who registered the repair.
	 *
	 * @param utenteReg the registering user to set
	 */
	public void setUtenteReg(Utente utenteReg) {
		this.utenteReg = utenteReg;
	}
}
