package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ModificaClienteDto {

    private UUID id;

	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
    private String nome;

    @Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
    private String cognome;

    @Email(message = "Formato dell'email non valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
    private String telefono;

    public ModificaClienteDto() {
    }

    public ModificaClienteDto(UUID id) {
        this.id = id;
    }

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
}
