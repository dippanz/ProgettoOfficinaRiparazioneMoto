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

/**
 * 
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private UUID id;
	@Column(name = "Nome")
	private String nome;
	@Column(name = "Cognome")
	private String cognome;
	@Column(name = "Telefono")
	private String telefono;
	@Column(name = "Email")
	private String email;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_utente_reg", nullable = false)
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
