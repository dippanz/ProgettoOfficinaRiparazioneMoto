package it.officina.OfficinaRiparazioneMoto.dto;

import java.util.UUID;

public class MotoDto {
    
    private UUID id;
	private String modello;
	private String targa;
	private UUID idCliente;
	private UUID idUtenteReg;

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
    public UUID getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }
    public UUID getIdUtenteReg() {
        return idUtenteReg;
    }
    public void setIdUtenteReg(UUID idUtenteReg) {
        this.idUtenteReg = idUtenteReg;
    }
}
