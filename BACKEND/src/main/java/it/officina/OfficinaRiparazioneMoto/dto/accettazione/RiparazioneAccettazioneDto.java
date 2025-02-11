package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.util.UUID;

public class RiparazioneAccettazioneDto {

    private UUID idRiparazione;
    private String codice;
    private String targa;
    private String emailCliente;
    private String stato;
    
    public UUID getIdRiparazione() {
        return idRiparazione;
    }
    public void setIdRiparazione(UUID idRiparazione) {
        this.idRiparazione = idRiparazione;
    }
    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }
    public String getEmailCliente() {
        return emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    public String getStato() {
        return stato;
    }
    public void setStato(String stato) {
        this.stato = stato;
    }
}