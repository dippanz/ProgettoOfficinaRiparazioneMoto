package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.time.LocalDateTime;
import java.util.UUID;

public class DettaglioAccettazioneDto {
    //riparazione
    private UUID id;
    private String descrizioneProblema;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String codiceServizio;
    private String statoRiparazione;

    //moto
	private String modello;
	private String targa;

    //cliente
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getDescrizioneProblema() {
        return descrizioneProblema;
    }
    public void setDescrizioneProblema(String descrizione) {
        this.descrizioneProblema = descrizione;
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
    public String getStatoRiparazione() {
        return statoRiparazione;
    }
    public void setStatoRiparazione(String statoRiparazione) {
        this.statoRiparazione = statoRiparazione;
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
}
