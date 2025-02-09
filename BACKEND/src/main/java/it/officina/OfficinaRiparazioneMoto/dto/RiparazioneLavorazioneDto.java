package it.officina.OfficinaRiparazioneMoto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RiparazioneLavorazioneDto {

    private UUID id;
	private String descrizione;
    private BigDecimal ore;
	private LocalDateTime dataInserimento;
	private UUID idRiparazione;
    
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
    public BigDecimal getOre() {
        return ore;
    }
    public void setOre(BigDecimal ore) {
        this.ore = ore;
    }
    public LocalDateTime getDataInserimento() {
        return dataInserimento;
    }
    public void setDataInserimento(LocalDateTime dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
    public UUID getIdRiparazione() {
        return idRiparazione;
    }
    public void setIdRiparazione(UUID riparazione) {
        this.idRiparazione = riparazione;
    }
}
