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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vendas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message="Adicione uma observação à venda.")
	@Size(min = 5, max = 50, message = "A observação deve ter entre 5 e 50 caracteres")
	private String observacao;
	private Double total;
	
	
	@NotNull(message="A venda deve conter ao menos um produto!!")
	@ManyToMany
	@JoinTable(name = "venda_has_produto")
	private List<Produtos> produtos;
	
	@JsonIgnoreProperties("vendas")
	private Funcionarios funcionario;
	
	@NotNull(message = "Um cliente deve estar associado à venda!!!")
	@JsonIgnoreProperties("vendas")
	private Clientes cliente;
	
	
}
