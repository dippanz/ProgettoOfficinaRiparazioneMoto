package it.officina.OfficinaRiparazioneMoto.dto.admin;

import java.util.UUID;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for modifying motorcycle details.
 * <p>
 * This DTO is used in the admin module to update a motorcycle's information,
 * specifically its license plate (targa) and model. Validation annotations
 * ensure that the provided values meet the required format and length
 * restrictions.
 * </p>
 */
public class ModificaMotoDto {

    private UUID id;

    @Pattern(regexp = "^[A-Z]{1,3}[0-9]{1,4}[A-Z]{1,3}$|^[A-Z]{1,3}-[0-9]{1,4}-[A-Z]{1,3}$|^[A-Z0-9]{6,8}$", message = "La targa può contenere solo lettere, numeri e trattini")
    private String targa;

    @Size(max = 80, message = "Il modello non può superare i 80 caratteri")
    private String modello;

    /**
     * Default constructor.
     */
    public ModificaMotoDto() {
    }

    /**
     * Constructs a {@code ModificaMotoDto} with the specified motorcycle
     * identifier.
     *
     * @param id the unique identifier of the motorcycle to be modified
     */
    public ModificaMotoDto(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the motorcycle.
     *
     * @return the motorcycle's UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the motorcycle.
     *
     * @param id the motorcycle's UUID to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the model of the motorcycle.
     *
     * @return the motorcycle model as a String
     */
    public String getModello() {
        return modello;
    }

    /**
     * Sets the model of the motorcycle.
     *
     * @param modello the motorcycle model to set
     */
    public void setModello(String modello) {
        this.modello = modello;
    }

    /**
     * Gets the license plate (targa) of the motorcycle.
     *
     * @return the motorcycle license plate as a String
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Sets the license plate (targa) of the motorcycle.
     *
     * @param targa the motorcycle license plate to set
     */
    public void setTarga(String targa) {
        this.targa = targa;
    }
}
