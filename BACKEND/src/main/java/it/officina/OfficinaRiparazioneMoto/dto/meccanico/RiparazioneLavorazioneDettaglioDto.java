package it.officina.OfficinaRiparazioneMoto.dto.meccanico;

import java.math.BigDecimal;

public class RiparazioneLavorazioneDettaglioDto {

    private String descrizioneLavorazione;
    private BigDecimal ore;

    public String getDescrizioneLavorazione() {
        return descrizioneLavorazione;
    }
    public void setDescrizioneLavorazione(String descrizioneLavorazione) {
        this.descrizioneLavorazione = descrizioneLavorazione;
    }
    public BigDecimal getOre() {
        return ore;
    }
    public void setOre(BigDecimal ore) {
        this.ore = ore;
    }
}
