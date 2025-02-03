package it.officina.OfficinaRiparazioneMoto.dto;

import java.util.UUID;

public class ClienteDto {
    
	private UUID id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private UUID utenteReg;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UUID getUtenteReg() {
        return utenteReg;
    }
    public void setUtenteReg(UUID utenteReg) {
        this.utenteReg = utenteReg;
    }
}
