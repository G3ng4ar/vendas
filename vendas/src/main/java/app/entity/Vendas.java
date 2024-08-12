package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Vendas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String observacao;
	private Double total;
	
	@ManyToMany
	@JoinTable(name = "venda_has_produto")
	private List<Produtos> produtos;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("vendas")
	private Funcionarios funcionario;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("vendas")
	private Clientes cliente;
	
	public Vendas() {
		
	}
	
	public Vendas(long id, String observacao, Double total, Funcionarios funcionario, List<Produtos> produtos) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.total = total;
		this.funcionario = funcionario;
		this.produtos = produtos;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

	public Funcionarios getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionarios funcionario) {
		this.funcionario = funcionario;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
