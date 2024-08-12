package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Clientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String telefone;
	private int idade;
	private String endereco;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente")
	@JsonIgnoreProperties("cliente")
	private List<Vendas> vendas;
	
	public Clientes() {
		
	}
	
	public Clientes(long id, String nome, String email, String telefone, int idade, String endereco, List<Vendas> vendas) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.idade = idade;
		this.endereco = endereco;
		this.vendas = vendas;
	}
	
	public List<Vendas> getVendas() {
		return vendas;
	}

	public void setVendas(List<Vendas> vendas) {
		this.vendas = vendas;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
