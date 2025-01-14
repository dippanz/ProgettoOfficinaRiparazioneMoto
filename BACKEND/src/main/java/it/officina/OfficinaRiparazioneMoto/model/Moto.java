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
@Table(name = "MOTO")
public class Moto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private UUID id;
	@Column(name = "Modello")
	private String modello;
	@Column(name = "Targa")
	private String targa;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_cliente", nullable = false)
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_utente_reg", nullable = false)
	private Utente utenteReg;
	@OneToMany(mappedBy = "moto", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Riparazione> riparazioni;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Utente getUtenteReg() {
		return utenteReg;
	}
	public void setUtenteReg(Utente utenteReg) {
		this.utenteReg = utenteReg;
	}
	public List<Riparazione> getRiparazioni() {
		return riparazioni;
	}
	public void setRiparazioni(List<Riparazione> riparazioni) {
		this.riparazioni = riparazioni;
	}
}
