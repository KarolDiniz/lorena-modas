package br.edu.ifpb.mt.dac.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity 
@Table(name = "telefone", uniqueConstraints = {@UniqueConstraint(name = "UC_TELEFONE", columnNames = {"numero"})})

public class Telefone{
	
	public Telefone() {}
	
	public Telefone(String n, Cliente c) {
		this.numero = n;
		this.cliente = c;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", precision = 16)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id_fk", nullable = false)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
