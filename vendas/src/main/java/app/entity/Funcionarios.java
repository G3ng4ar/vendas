package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Funcionarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Pattern(regexp = "^(?=.*\\s.+\\s).{2,}$", message = "O nome do funcionario deve conter nome e sobrenome")
	private String nome;
	
	@NotBlank(message="Email é obrigatório!")
	private String email;
	
	@Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "O CEP deve conter o seguinte formato: XXXXX-XXX")
	private String cep;
	
	@Pattern(regexp = "^(\\(\\d{2}\\) \\d{4,5}-\\d{4})$", message = "Corriga o telefone para o formato (XX) XXXX-XXXX")
	private String telefone;
	
	@CPF(message = "CPF inválido!")
	private String cpf;
	
	@Min(value = 16,message="Idade mínima é 16!")
	private int idade;
	
	@NotBlank(message= "Endereço é obrigatório!")
	private String endereco;
	private String funcao;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "funcionario")
	@JsonIgnoreProperties("funcionario")
	private List<Vendas> vendas;
	
}
