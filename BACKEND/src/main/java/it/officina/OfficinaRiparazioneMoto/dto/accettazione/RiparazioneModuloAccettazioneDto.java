package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.util.UUID;

/**
 * Data Transfer Object for representing a repair module in the acceptance
 * process.
 * <p>
 * This DTO provides summary information about a repair, including its unique
 * identifier,
 * service code, vehicle license plate, client's email, and the current repair
 * state.
 * It is used in the acceptance module to display a concise list of repair
 * records.
 * </p>
 */
public class RiparazioneModuloAccettazioneDto {

    private UUID idRiparazione;
    private String codice;
    private String targa;
    private String emailCliente;
    private String stato;

    /**
     * Constructs a new {@code RiparazioneModuloAccettazioneDto} with the specified
     * parameters.
     *
     * @param idRiparazione the unique identifier of the repair
     * @param codice        the service code of the repair
     * @param targa         the vehicle's license plate
     * @param emailCliente  the email of the client associated with the repair
     * @param stato         the current state of the repair
     */
    public RiparazioneModuloAccettazioneDto(UUID idRiparazione, String codice, String targa, String emailCliente,
            String stato) {
        this.idRiparazione = idRiparazione;
        this.codice = codice;
        this.targa = targa;
        this.emailCliente = emailCliente;
        this.stato = stato;
    }

    /**
     * Default constructor.
     */
    public RiparazioneModuloAccettazioneDto() {
    }

    /**
     * Gets the service code of the repair.
     *
     * @return the service code as a String
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the service code of the repair.
     *
     * @param codice the service code to set
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Gets the vehicle's license plate.
     *
     * @return the license plate as a String
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Sets the vehicle's license plate.
     *
     * @param targa the license plate to set
     */
    public void setTarga(String targa) {
        this.targa = targa;
    }

    /**
     * Gets the client's email associated with the repair.
     *
     * @return the client's email as a String
     */
    public String getEmailCliente() {
        return emailCliente;
    }

    /**
     * Sets the client's email associated with the repair.
     *
     * @param emailCliente the client's email to set
     */
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    /**
     * Gets the current state of the repair.
     *
     * @return the repair state as a String
     */
    public String getStato() {
        return stato;
    }

    /**
     * Sets the current state of the repair.
     *
     * @param stato the repair state to set
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Gets the unique identifier of the repair.
     *
     * @return the repair ID as a {@link UUID}
     */
    public UUID getIdRiparazione() {
        return idRiparazione;
    }

    /**
     * Sets the unique identifier of the repair.
     *
     * @param idRiparazione the repair ID to set
     */
    public void setIdRiparazione(UUID idRiparazione) {
        this.idRiparazione = idRiparazione;
    }
}
