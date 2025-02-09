/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 
 */
@Entity
@Table(name = "\"RIPARAZIONE\"")
public class Riparazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "descrizione_problema")
	private String descrizioneProblema;
	
	@Column(name = "\"dataInizio\"", updatable = false, insertable = false)
	private LocalDateTime dataInizio;
	
	@Column(name = "\"dataFine\"")
	private LocalDateTime dataFine;
	
	@NotBlank(message = "Codice servizio obbligatorio")
	@Pattern(
			regexp = "^[A-Z0-9]{6,25}$", 
			message = "Il codice non rispetta i criteri richiesti"
	)
	@Column(name = "codice_servizio", unique = true, nullable = false)
	private String codiceServizio;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_moto", nullable = false)
	private Moto moto;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente_mec", nullable = true)
	private Utente utenteMec;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stato", nullable = false)
	private StatoRiparazione stato;

	@OneToMany(mappedBy = "riparazione", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<RiparazioneLavorazione> lavorazioni;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getDescrizioneProblema() {
		return descrizioneProblema;
	}
	public void setDescrizioneProblema(String descrizioneProblema) {
		this.descrizioneProblema = descrizioneProblema;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}
	public Moto getMoto() {
		return moto;
	}
	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	public Utente getUtenteMec() {
		return utenteMec;
	}
	public void setUtenteMec(Utente utenteMec) {
		this.utenteMec = utenteMec;
	}
	public StatoRiparazione getStato() {
		return stato;
	}
	public void setStato(StatoRiparazione stato) {
		this.stato = stato;
	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public List<RiparazioneLavorazione> getLavorazioni() {
		return lavorazioni;
	}
	public void setLavorazioni(List<RiparazioneLavorazione> lavorazioni) {
		this.lavorazioni = lavorazioni;
	}
}
