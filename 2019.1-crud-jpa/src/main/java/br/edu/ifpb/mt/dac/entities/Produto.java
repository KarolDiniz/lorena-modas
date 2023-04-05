package br.edu.ifpb.mt.dac.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "produto")
public class Produto {
	
	public Produto() {}
	
	public Produto(String cor, String tamanho, Double valor, String tipo) {
		this.cor = cor;
		tipoTamanho(tamanho);
		this.valor = valor;
		tipoProduto(tipo);	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "cor",nullable = false, length = 50)
	private String cor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tamanho", nullable = false, length = 20)
	private TipoTamanho tamanho;
	
	@Column(name = "valor", precision = 6, scale = 2,nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoProduto tipo;	
	
	private void tipoProduto(String tipo) {
		
		switch(tipo) {
		
		case "CAMISA":
				this.tipo = TipoProduto.CAMISA;
				break;
			case "TERNO":
				this.tipo = TipoProduto.TERNO;
				break;
			case "VESTIDO":
				this.tipo = TipoProduto.VESTIDO;
				break;
			case "SAIA":
				this.tipo = TipoProduto.SAIA;
				break;
			case "SHORT":
				this.tipo = TipoProduto.SHORT;
				break;
			case "CALCA":
				this.tipo = TipoProduto.CALCA;
				break;
			case "MACACAO":
				this.tipo = TipoProduto.MACACAO;
				break;
			case "MEIA":
				this.tipo = TipoProduto.MEIA;
				break;
			case "LUVA":
				this.tipo = TipoProduto.LUVA;
				break;
			case "GRAVATA":
				this.tipo = TipoProduto.GRAVATA;	
				break;
			case "ROUPA_INTIMA":
				this.tipo = TipoProduto.ROUPA_INTIMA;
		}
	}
	private void tipoTamanho(String tipo) {
		
		switch(tipo) {
		
			case "P":
				this.tamanho = TipoTamanho.P;
				break;
			case "PP":
				this.tamanho = TipoTamanho.PP;
				break;
			case "M":
				this.tamanho = TipoTamanho.M;
				break;
			case "G":
				this.tamanho = TipoTamanho.G;
				break;
			case "GG":
				this.tamanho = TipoTamanho.GG;
		}
	}

	public TipoTamanho getTipoTamanho() {
		return tamanho;
	}
	
	public TipoProduto getTipoProduto() {
		return tipo;
	}
	public String getCor() {
		return cor;
	}
	public Double getValor() {
		return valor;
	}

	public TipoTamanho  setTipoTamanho() {
		return tamanho;
	}
	public void  setTipoTamanho(TipoTamanho tamanho) {
		this.tamanho = tamanho;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public boolean equals(Produto produto) {
		return id.equals(produto.id);
	}
	
}
