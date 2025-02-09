package it.officina.OfficinaRiparazioneMoto.dto.meccanico;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class AggiungiLavorazioneDto {

    private UUID idRiparazione;

    @Size(max = 500, message = "La descrizione non può superare i 500 caratteri")
    private String descrizione;

    @DecimalMin(value = "0.0", message = "Le ore devono essere positive")
    @Digits(integer = 3, fraction = 2, message = "Le ore devono avere al massimo 3 cifre intere e 2 decimali")
    private BigDecimal ore;

    public AggiungiLavorazioneDto() {
    }

    public AggiungiLavorazioneDto(UUID idRiparazione) {
        this.idRiparazione = idRiparazione;
    }

    public UUID getIdRiparazione() {
        return idRiparazione;
    }
    public void setIdRiparazione(UUID idRiparazione) {
        this.idRiparazione = idRiparazione;
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
}
