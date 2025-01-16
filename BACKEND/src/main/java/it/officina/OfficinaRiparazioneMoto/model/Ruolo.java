/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
@Entity
@Table(name = "RUOLO")
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private UUID id;
	
	@NotNull
	@Column(name = "Nome", unique = true)
	private String nome;
	
	@ManyToMany
    @JoinTable(
        name = "UTENTE_RUOLO", // Nome della tabella di join
        joinColumns = @JoinColumn(name = "Id_ruolo", referencedColumnName = "Id"),
        inverseJoinColumns = @JoinColumn(name = "Id_utente", referencedColumnName = "Id")
    )
	private List<Utente> utenti;
	
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
	public List<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
}
