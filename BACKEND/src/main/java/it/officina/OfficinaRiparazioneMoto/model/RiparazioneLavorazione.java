package it.officina.OfficinaRiparazioneMoto.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name = "\"RIPARAZIONE_LAVORAZIONE\"")
public class RiparazioneLavorazione {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String descrizione;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Le ore devono essere positive")
    @Digits(integer = 3, fraction = 2, message = "Le ore devono avere al massimo 3 cifre intere e 2 decimali")
    private BigDecimal ore;
	
	@Column(name = "data_inserimento", insertable = false, updatable = false)
	private LocalDateTime dataInserimento;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_riparazione", nullable = false)
	private Riparazione riparazione;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataInserimento() {
        return dataInserimento;
    }

    public Riparazione getRiparazione() {
        return riparazione;
    }

    public void setRiparazione(Riparazione riparazione) {
        this.riparazione = riparazione;
    }

    public BigDecimal getOre() {
        return ore;
    }

    public void setOre(BigDecimal ore) {
        this.ore = ore;
    }
}
