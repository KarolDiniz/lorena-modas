package br.edu.ifpb.mt.dac.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity 
@Table(name = "compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Compra(){}
	
	@Column(name = "valor_total", precision = 6, scale = 2, nullable = false)
	private Double valorTotal;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data",nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "funcionario_id_fk", nullable = false)
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id_fk", nullable = false)
	private Cliente cliente;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "compra_produto", joinColumns = @JoinColumn(name = "compra_id_fk"), 
	inverseJoinColumns = @JoinColumn(name = "produto_id_fk"))
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public int hashCode() {
		return 0;
	}

	public boolean equals(Compra compra) {
		return id.equals(compra.id);
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setDate(Date data) {
		this.date= data;
	}
	public Date getDate() {
		return date;
	}
	
	
}
