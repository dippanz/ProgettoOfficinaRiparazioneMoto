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

/**
 * Entity representing a work item (lavorazione) performed during a repair.
 * <p>
 * This entity stores details about a specific work item associated with a
 * repair,
 * including a description, the number of hours worked, and the insertion date.
 * It is
 * linked to a {@link Riparazione} entity.
 * </p>
 */
@Entity
@Table(name = "\"RIPARAZIONE_LAVORAZIONE\"")
public class RiparazioneLavorazione {

    /**
     * The unique identifier for the work item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * A description of the work performed.
     */
    @Column(nullable = false)
    private String descrizione;

    /**
     * The number of hours spent on this work item.
     * <p>
     * The value must be positive and can have up to 3 integer digits and 2 decimal
     * places.
     * </p>
     */
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Le ore devono essere positive")
    @Digits(integer = 3, fraction = 2, message = "Le ore devono avere al massimo 3 cifre intere e 2 decimali")
    private BigDecimal ore;

    /**
     * The date and time when this work item was inserted.
     * <p>
     * This field is managed by the database and is not insertable or updatable.
     * </p>
     */
    @Column(name = "data_inserimento", insertable = false, updatable = false)
    private LocalDateTime dataInserimento;

    /**
     * The repair associated with this work item.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_riparazione", nullable = false)
    private Riparazione riparazione;

    /**
     * Gets the unique identifier of the work item.
     *
     * @return the UUID of the work item
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the work item.
     *
     * @param id the UUID to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the description of the work performed.
     *
     * @return the work description
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the description of the work performed.
     *
     * @param descrizione the work description to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Gets the date and time when this work item was inserted.
     *
     * @return the insertion date and time
     */
    public LocalDateTime getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Gets the repair associated with this work item.
     *
     * @return the associated {@link Riparazione}
     */
    public Riparazione getRiparazione() {
        return riparazione;
    }

    /**
     * Sets the repair associated with this work item.
     *
     * @param riparazione the {@link Riparazione} to associate
     */
    public void setRiparazione(Riparazione riparazione) {
        this.riparazione = riparazione;
    }

    /**
     * Gets the number of hours spent on this work item.
     *
     * @return the number of hours
     */
    public BigDecimal getOre() {
        return ore;
    }

    /**
     * Sets the number of hours spent on this work item.
     *
     * @param ore the number of hours to set
     */
    public void setOre(BigDecimal ore) {
        this.ore = ore;
    }
}
