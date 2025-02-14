package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.UUID;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ModificaMotoDto {

    private UUID id;
    
	@Pattern(regexp = "^[A-Z]{1,3}[0-9]{1,4}[A-Z]{1,3}$|^[A-Z]{1,3}-[0-9]{1,4}-[A-Z]{1,3}$|^[A-Z0-9]{6,8}$", message = "La targa può contenere solo lettere, numeri e trattini")
    private String targa;

    @Size(max = 80, message = "Il modello non può superare i 80 caratteri")
    private String modello;
    
    public ModificaMotoDto() {
    }

    public ModificaMotoDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getModello() {
        return modello;
    }
    public void setModello(String modello) {
        this.modello = modello;
    }
    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }
}
