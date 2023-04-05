package br.edu.ifpb.mt.dac.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "cliente")
public class Cliente {
	
	public Cliente() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //O banco gera um id aleatório
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	@Column(name = "numero")
	private List<Telefone> telefones;
	
	@Embedded // Tudo está dentro do objeto endereco vai estar na mesma tabela de quem está colocando o emdebbed
    private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco(){
		return endereco;
	}
	
	public void setEndereco(Endereco e){
		this.endereco = e;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	public boolean equals(Cliente cliente) {
		return id.equals(cliente.id);
	}

	public String toString() {
		return ""+id;
	}
}
