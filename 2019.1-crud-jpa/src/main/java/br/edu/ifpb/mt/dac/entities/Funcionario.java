package br.edu.ifpb.mt.dac.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "funcionario")
public class Funcionario {
	
	public Funcionario() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoFuncionario tipo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoFuncionario getTipo() {
		return tipo;
	}

	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, tipo);
	}

	public boolean equals(Funcionario func) {
		return id.equals(func.id);
	}
	
	@Override
	public String toString() {
		return ""+id;
	}
	
}
