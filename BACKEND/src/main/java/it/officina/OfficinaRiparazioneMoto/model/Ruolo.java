/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	
	@Column(name = "Nome")
	private String nome;
	
	@OneToMany(mappedBy = "ruolo", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
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
