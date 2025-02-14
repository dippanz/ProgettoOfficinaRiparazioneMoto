package it.officina.OfficinaRiparazioneMoto.dto.accettazione;

import java.util.UUID;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for capturing client and vehicle information during the
 * acceptance process.
 * <p>
 * This DTO is used to collect data from the acceptance form including client
 * details (name, surname, email,
 * telephone) and vehicle details (license plate, model) along with a
 * description of the problem.
 * It also supports referencing an existing client or vehicle via their unique
 * identifiers.
 * </p>
 */
public class ClienteVeicoloDto {

    @Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
    private String nome;

    @Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
    private String cognome;

    @Email(message = "Formato dell'email non valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
    private String telefono;

    @Pattern(regexp = "^[A-Z]{1,3}[0-9]{1,4}[A-Z]{1,3}$|^[A-Z]{1,3}-[0-9]{1,4}-[A-Z]{1,3}$|^[A-Z0-9]{6,8}$", message = "La targa può contenere solo lettere, numeri e trattini")
    private String targa;

    @Size(max = 80, message = "Il modello non può superare i 80 caratteri")
    private String modello;

    @Size(max = 500, message = "La descrizione non può superare i 500 caratteri")
    private String descrizioneProblema;

    private UUID idCliente;
    private UUID idMoto;

    /**
     * Validates that an email is provided if no existing client or vehicle is
     * selected.
     *
     * @return {@code true} if an email is provided or an existing client/vehicle is
     *         selected, {@code false} otherwise.
     */
    @AssertTrue(message = "La email è obbligatoria se non hai selezionato un cliente esistente")
    public boolean isEmailValid() {
        return idCliente != null || idMoto != null || (email != null && !email.isBlank());
    }

    /**
     * Validates that a license plate is provided if no existing vehicle is
     * selected.
     *
     * @return {@code true} if a license plate is provided or an existing vehicle is
     *         selected, {@code false} otherwise.
     */
    @AssertTrue(message = "La targa è obbligatoria se non hai selezionato un veicolo esistente")
    public boolean isTargaValid() {
        return idMoto != null || (targa != null && !targa.isBlank());
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getDescrizioneProblema() {
        return descrizioneProblema;
    }

    public void setDescrizioneProblema(String descrizione) {
        this.descrizioneProblema = descrizione;
    }

    public UUID getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(UUID idMoto) {
        this.idMoto = idMoto;
    }
}
