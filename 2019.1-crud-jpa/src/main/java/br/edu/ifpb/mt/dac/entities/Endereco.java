package br.edu.ifpb.mt.dac.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	public Endereco(){}
	
	public Endereco(String rua, String bairro, String cep, String numero, String cidade){
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
	}
	
	@Column(name = "rua", nullable = false)
	private String rua;

	@Column(name = "bairro",nullable = false)
	private String bairro;
	
	@Column(name = "cep", precision = 9, nullable = false)
	private String cep;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Column(name= "cidade",nullable = false)
	private String cidade;
	
		
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}

