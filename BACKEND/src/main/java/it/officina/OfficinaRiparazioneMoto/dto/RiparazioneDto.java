package it.officina.OfficinaRiparazioneMoto.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class RiparazioneDto {
    
    private UUID id;
    private String descrizione;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String codiceServizio;
    private UUID idMoto;
    private UUID idUtenteMec;
    private String statoRiparazione;
    
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
    public LocalDateTime getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }
    public LocalDateTime getDataFine() {
        return dataFine;
    }
    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }
    public String getCodiceServizio() {
        return codiceServizio;
    }
    public void setCodiceServizio(String codiceServizio) {
        this.codiceServizio = codiceServizio;
    }
    public UUID getIdMoto() {
        return idMoto;
    }
    public void setIdMoto(UUID idMoto) {
        this.idMoto = idMoto;
    }
    public UUID getIdUtenteMec() {
        return idUtenteMec;
    }
    public void setIdUtenteMec(UUID idUtenteMec) {
        this.idUtenteMec = idUtenteMec;
    }
    public String getStatoRiparazione() {
        return statoRiparazione;
    }
    public void setStatoRiparazione(String statoRiparazione) {
        this.statoRiparazione = statoRiparazione;
    }
    
    
}
