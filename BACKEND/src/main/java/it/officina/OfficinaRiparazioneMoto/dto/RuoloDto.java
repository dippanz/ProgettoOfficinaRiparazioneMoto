package it.officina.OfficinaRiparazioneMoto.dto;

import java.util.List;
import java.util.UUID;

public class RuoloDto {
    
    private UUID id;
	private String nome;
	private List<UUID> utenti;
    
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
    public List<UUID> getUtenti() {
        return utenti;
    }
    public void setUtenti(List<UUID> utenti) {
        this.utenti = utenti;
    }
}
