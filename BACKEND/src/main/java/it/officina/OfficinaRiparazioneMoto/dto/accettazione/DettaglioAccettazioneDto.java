package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object representing the detailed acceptance information of a
 * repair.
 * <p>
 * This DTO aggregates data related to a repair including repair details (such
 * as problem description,
 * start and end dates, service code, and repair state), vehicle details (model
 * and license plate),
 * and client details (name, surname, telephone, and email).
 * Used to view the detail in the acceptances section.
 * </p>
 */
public class DettaglioAccettazioneDto {

    // Repair details
    private UUID id;
    private String descrizioneProblema;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String codiceServizio;
    private String statoRiparazione;

    // Vehicle details
    private String modello;
    private String targa;

    // Client details
    private String nome;
    private String cognome;
    private String telefono;
    private String email;

    /**
     * Gets the unique identifier of the repair.
     *
     * @return the repair ID as a {@link UUID}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the repair.
     *
     * @param id the repair ID to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the problem description of the repair.
     *
     * @return the problem description as a {@link String}
     */
    public String getDescrizioneProblema() {
        return descrizioneProblema;
    }

    /**
     * Sets the problem description of the repair.
     *
     * @param descrizione the problem description to set
     */
    public void setDescrizioneProblema(String descrizione) {
        this.descrizioneProblema = descrizione;
    }

    /**
     * Gets the start date and time of the repair.
     *
     * @return the start date and time as a {@link LocalDateTime}
     */
    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    /**
     * Sets the start date and time of the repair.
     *
     * @param dataInizio the start date and time to set
     */
    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    /**
     * Gets the end date and time of the repair.
     *
     * @return the end date and time as a {@link LocalDateTime}
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
     * Gets the service code associated with the repair.
     *
     * @return the service code as a {@link String}
     */
    public String getCodiceServizio() {
        return codiceServizio;
    }

    /**
     * Sets the service code associated with the repair.
     *
     * @param codiceServizio the service code to set
     */
    public void setCodiceServizio(String codiceServizio) {
        this.codiceServizio = codiceServizio;
    }

    /**
     * Gets the current state of the repair.
     *
     * @return the repair state as a {@link String}
     */
    public String getStatoRiparazione() {
        return statoRiparazione;
    }

    /**
     * Sets the current state of the repair.
     *
     * @param statoRiparazione the repair state to set
     */
    public void setStatoRiparazione(String statoRiparazione) {
        this.statoRiparazione = statoRiparazione;
    }

    /**
     * Gets the vehicle model.
     *
     * @return the vehicle model as a {@link String}
     */
    public String getModello() {
        return modello;
    }

    /**
     * Sets the vehicle model.
     *
     * @param modello the vehicle model to set
     */
    public void setModello(String modello) {
        this.modello = modello;
    }

    /**
     * Gets the vehicle license plate.
     *
     * @return the license plate as a {@link String}
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Sets the vehicle license plate.
     *
     * @param targa the license plate to set
     */
    public void setTarga(String targa) {
        this.targa = targa;
    }

    /**
     * Gets the client's first name.
     *
     * @return the client's first name as a {@link String}
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
     * Gets the client's surname.
     *
     * @return the client's surname as a {@link String}
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the client's surname.
     *
     * @param cognome the client's surname to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets the client's telephone number.
     *
     * @return the telephone number as a {@link String}
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the client's telephone number.
     *
     * @param telefono the telephone number to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the client's email address.
     *
     * @return the email address as a {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
