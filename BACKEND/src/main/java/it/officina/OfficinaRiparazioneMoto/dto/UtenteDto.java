package it.officina.OfficinaRiparazioneMoto.dto;

import java.util.List;
import java.util.UUID;

public class UtenteDto {
    
    private UUID id;
    private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String username;
	private List<String> ruoli;

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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<String> getRuoli() {
        return ruoli;
    }
    public void setRuoli(List<String> ruoli) {
        this.ruoli = ruoli;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
