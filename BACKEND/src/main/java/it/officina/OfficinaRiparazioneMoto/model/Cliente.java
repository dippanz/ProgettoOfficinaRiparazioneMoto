/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.model;

import java.util.List;
import java.util.UUID;

import it.officina.OfficinaRiparazioneMoto.converter.LowercaseConverter;
import it.officina.OfficinaRiparazioneMoto.converter.UppercaseConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
@Entity
@Table(name = "\"CLIENTE\"")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String nome;
	
	@Column(length = 256)
	@Convert(converter = UppercaseConverter.class)
	private String cognome;
	
	@Column(length = 256)
	private String telefono;
	
	@NotBlank(message = "Email obbligatoria")
	@Column(length = 256, unique = true, nullable = false)
	@Convert(converter = LowercaseConverter.class)
	private String email;
	
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
	public List<Moto> getListaMoto() {
		return listaMoto;
	}
	public void setListaMoto(List<Moto> listaMoto) {
		this.listaMoto = listaMoto;
	}
}
