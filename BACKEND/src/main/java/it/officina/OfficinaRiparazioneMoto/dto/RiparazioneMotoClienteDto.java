package it.officina.OfficinaRiparazioneMoto.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class RiparazioneMotoClienteDto {

    private UUID id;
    private String descrizione;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String codiceServizio;
    private UUID idUtenteMec;
    private String statoRiparazione;

    // moto
    private String modello;
    private String targa;
    private UUID idUtenteReg;

    // cliente
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
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
    public UUID getIdUtenteMec() {
        return idUtenteMec;
    }
    public void setIdUtenteMec(UUID idUtenteMec) {
        this.idUtenteMec = idUtenteMec;
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
    public UUID getIdUtenteReg() {
        return idUtenteReg;
    }
    public void setIdUtenteReg(UUID idUtenteReg) {
        this.idUtenteReg = idUtenteReg;
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
