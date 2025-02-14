package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.util.UUID;

/**
 * Data Transfer Object for representing summary repair information in the
 * acceptance process.
 * <p>
 * This DTO provides key details about a repair, such as its unique identifier,
 * service code,
 * associated vehicle license plate, client's email, and the current repair
 * state.
 * It is used primarily for displaying repair information in the acceptance
 * module.
 * </p>
 */
public class RiparazioneAccettazioneDto {

    private UUID idRiparazione;
    private String codice;
    private String targa;
    private String emailCliente;
    private String stato;

    /**
     * Gets the unique identifier of the repair.
     *
     * @return the repair's UUID
     */
    public UUID getIdRiparazione() {
        return idRiparazione;
    }

    /**
     * Sets the unique identifier of the repair.
     *
     * @param idRiparazione the repair's UUID to set
     */
    public void setIdRiparazione(UUID idRiparazione) {
        this.idRiparazione = idRiparazione;
    }

    /**
     * Gets the service code associated with the repair.
     *
     * @return the service code as a String
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the service code associated with the repair.
     *
     * @param codice the service code to set
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Gets the vehicle license plate associated with the repair.
     *
     * @return the license plate as a String
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Sets the vehicle license plate associated with the repair.
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
}
