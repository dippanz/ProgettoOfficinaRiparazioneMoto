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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
@Entity
@Table(name = "\"UTENTE\"")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(length = 256)
	private String nome;
	
	@Column(length = 256)
	private String cognome;
	
	@Column(length = 256)
	private String telefono;
	
	@NotBlank(message = "Email obbligatoria")
	@Column(nullable = false, unique = true, length = 256)
	private String email;
	
	@Column(unique = true, length = 256)
	private String username;
	
	@NotBlank(message = "HashPassword obbligatoria")
	@Column(name = "\"hashPassword\"", nullable = false)
	private String hashPassword;
	
	@ManyToMany
    @JoinTable(
        name = "\"UTENTE_RUOLO\"", // Nome della tabella di join
        joinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_ruolo", referencedColumnName = "id")
    )
	private List<Ruolo> ruoli;
	
	@OneToMany(mappedBy = "utenteReg", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> listaRiparazioni;
	
	@OneToMany(mappedBy = "utenteMec", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;
	
	public Utente() {
		
	}
	
	public Utente(String nome, String cognome, String telefono, String email, String username, String hashpassword) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.username = username;
		this.hashPassword = hashpassword;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	public List<Riparazione> getListaRiparazioni() {
		return listaRiparazioni;
	}
	public void setListaRiparazioni(List<Riparazione> listaRiparazioni) {
		this.listaRiparazioni = listaRiparazioni;
	}
}
