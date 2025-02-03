package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

public class RiparazioniModuloAccettazioneDto {
    
    private String codice;
    private String targa;
    private String emailCliente;
    private String stato;

    public RiparazioniModuloAccettazioneDto(String codice, String targa, String emailCliente, String stato) {
        this.codice = codice;
        this.targa = targa;
        this.emailCliente = emailCliente;
        this.stato = stato;
    }

    public RiparazioniModuloAccettazioneDto() {
    }

    // Getters and Setters
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