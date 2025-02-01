package it.officina.OfficinaRiparazioneMoto.dto.publics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CercaRiparazioneDto {

    @Pattern(regexp = "^[A-Z0-9]{6,25}$", message = "Inserire caratteri validi")
    private String codiceServizio;

    @NotBlank(message = "Targa obbligatoria")
    @Pattern(
        regexp = "^[A-Z]{1,3}[0-9]{1,4}[A-Z]{1,3}$|^[A-Z]{1,3}-[0-9]{1,4}-[A-Z]{1,3}$|^[A-Z0-9]{6,8}$", 
        message = "Inserire una targa valida")
    private String targa;

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
}
