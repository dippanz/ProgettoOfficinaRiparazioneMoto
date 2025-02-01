package it.officina.OfficinaRiparazioneMoto.dto.publics;

public class RiparazioneDettaglioGeneraleDto {

    private String codiceServizio;
    private String targa;
    private String stato;
    private String dataInizio;
    private String dataFine;
    
    public String getCodiceServizio() {
        return codiceServizio;
    }
    public void setCodiceServizio(String codiceServizio) {
        this.codiceServizio = codiceServizio;
    }
    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }
    public String getStato() {
        return stato;
    }
    public void setStato(String stato) {
        this.stato = stato;
    }
    public String getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }
    public String getDataFine() {
        return dataFine;
    }
    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

}
