package it.prova.societaimpiegatoprogetto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "progetto")
public class Progetto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cliente")
	private String cliente;
	@Column(name = "durata")
	private int durata;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "progetti")
	private Set<Impiegato> impiegati = new HashSet<Impiegato>();
	
	public Progetto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Progetto(Long id, String nome, String cliente, int durata, Set<Impiegato> impiegati) {
		super();
		this.id = id;
		this.nome = nome;
		this.cliente = cliente;
		this.durata = durata;
		this.impiegati = impiegati;
	}

	public Progetto(String nome, String cliente, int durata, Set<Impiegato> impiegati) {
		super();
		this.nome = nome;
		this.cliente = cliente;
		this.durata = durata;
		this.impiegati = impiegati;
	}

	public Progetto(String nome, String cliente, int durata) {
		super();
		this.nome = nome;
		this.cliente = cliente;
		this.durata = durata;
	}

	public Progetto(Long id, String nome, String cliente, int durata) {
		super();
		this.id = id;
		this.nome = nome;
		this.cliente = cliente;
		this.durata = durata;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public Set<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(Set<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

	@Override
	public String toString() {
		return "Progetto [nome=" + nome + ", cliente=" + cliente + ", durata=" + durata + ", impiegati=" + impiegati
				+ "]";
	}

}
