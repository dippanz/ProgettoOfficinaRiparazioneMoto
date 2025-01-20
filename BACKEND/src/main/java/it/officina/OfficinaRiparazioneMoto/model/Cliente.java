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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 
 */
@Entity
@Table(name = "\"CLIENTE\"")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Size(min = 2, max = 50, message = "Il nome non può superare i 50 caratteri")
	@Column(length = 256)
	private String nome;
	
	@Size(min = 2, max = 50, message = "Il cognome non può superare i 50 caratteri")
	@Column(length = 256)
	private String cognome;
	
	@Pattern(regexp = "^\\+?[0-9]{1,3}?\\s?[0-9]{6,10}$", message = "Formato del telefono non valido")
	@Column(length = 256)
	private String telefono;
	
	@NotNull(message = "Email obbligatoria")
	@Email
	@Column(length = 256, unique = true)
	private String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente_reg", nullable = false)
	private Utente utenteReg;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Moto> listaMoto;
	
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
	public Utente getUtenteReg() {
		return utenteReg;
	}
	public void setUtenteReg(Utente utenteReg) {
		this.utenteReg = utenteReg;
	}
	public List<Moto> getListaMoto() {
		return listaMoto;
	}
	public void setListaMoto(List<Moto> listaMoto) {
		this.listaMoto = listaMoto;
	}
}
