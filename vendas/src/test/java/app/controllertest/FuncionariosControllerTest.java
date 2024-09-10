package app.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import app.controller.FuncionariosController;
import app.entity.Funcionarios;
import app.service.FuncionariosService;

@SpringBootTest
public class FuncionariosControllerTest {

	@Autowired
	FuncionariosController funcionariosController;

	@MockBean
	FuncionariosService funcionariosService;

	@BeforeEach
	void setup() {
		Funcionarios funcionarioCadastrado = new Funcionarios();
		funcionarioCadastrado.setId(1L);
		funcionarioCadastrado.setNome("Pedro Silva");
		funcionarioCadastrado.setEmail("pedro.silva@email.com");
		funcionarioCadastrado.setCep("12345-678");
		funcionarioCadastrado.setTelefone("(11) 98765-4321");
		funcionarioCadastrado.setCpf("123.456.789-00");
		funcionarioCadastrado.setIdade(30);
		funcionarioCadastrado.setEndereco("Rua A, 123");
		funcionarioCadastrado.setFuncao("Gerente");

		// Mock do save
		Mockito.when(this.funcionariosService.save(Mockito.any(Funcionarios.class))).thenReturn(funcionarioCadastrado);

		// Mock do update
		Mockito.when(this.funcionariosService.update(Mockito.any(Funcionarios.class), Mockito.eq(1L)))
				.thenReturn(funcionarioCadastrado);

		// Mock do findById
		Mockito.when(this.funcionariosService.findById(1L)).thenReturn(funcionarioCadastrado);

		// Mock do findAll
		List<Funcionarios> listaFuncionarios = new ArrayList<>();
		listaFuncionarios.add(new Funcionarios(1L, "Ana Costa", "ana.costa@email.com", "98765-432", "(11) 99999-9999",
				"987.654.321-00", 28, "Rua B, 456", "Vendedora", null));
		listaFuncionarios.add(new Funcionarios(2L, "Carlos Almeida", "carlos.almeida@email.com", "54321-987",
				"(21) 88888-8888", "123.321.123-32", 35, "Rua C, 789", "Gerente de Vendas", null));
		Mockito.when(this.funcionariosService.findAll()).thenReturn(listaFuncionarios);

		// Mock do delete
		Mockito.doNothing().when(this.funcionariosService).delete(Mockito.any(Funcionarios.class), Mockito.eq(1L));
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Cadastrar funcionário com sucesso")
	void cadastrarFuncionarioSucesso() {
		Funcionarios funcionario = new Funcionarios();
		funcionario.setNome("Pedro Silva");
		funcionario.setEmail("pedro.silva@email.com");
		funcionario.setCep("12345-678");
		funcionario.setTelefone("(11) 98765-4321");
		funcionario.setCpf("123.456.789-00");
		funcionario.setIdade(30);
		funcionario.setEndereco("Rua A, 123");
		funcionario.setFuncao("Gerente");

		ResponseEntity<String> retorno = this.funcionariosController.save(funcionario);
		HttpStatusCode status = retorno.getStatusCode();
		String mensagem = retorno.getBody();

		assertEquals(HttpStatus.OK, status);
		assertEquals("Funcionario cadastrado com sucesso!", mensagem);
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Atualizar funcionário com sucesso")
	void atualizarFuncionarioSucesso() {
		Funcionarios funcionario = new Funcionarios();
		funcionario.setNome("Pedro Silva Atualizado");
		funcionario.setEmail("pedro.silva@email.com");
		funcionario.setCep("12345-678");
		funcionario.setTelefone("(11) 98765-4321");
		funcionario.setCpf("123.456.789-00");
		funcionario.setIdade(30);
		funcionario.setEndereco("Rua A, 123");
		funcionario.setFuncao("Gerente");

		ResponseEntity<String> retorno = this.funcionariosController.update(funcionario, 1L);
		HttpStatusCode status = retorno.getStatusCode();
		String mensagem = retorno.getBody();

		assertEquals(HttpStatus.OK, status);
		assertEquals("Funcionario atualizado com sucesso!", mensagem);
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Buscar funcionário por ID com sucesso")
	void buscarFuncionarioPorIdSucesso() {
		ResponseEntity<Funcionarios> retorno = this.funcionariosController.findById(new Funcionarios(), 1L);
		Funcionarios funcionario = retorno.getBody();
		HttpStatusCode status = retorno.getStatusCode();

		assertEquals(HttpStatus.OK, status);
		assertEquals(1L, funcionario.getId());
		assertEquals("Pedro Silva", funcionario.getNome());
		assertEquals("pedro.silva@email.com", funcionario.getEmail());
		assertEquals("12345-678", funcionario.getCep());
		assertEquals("(11) 98765-4321", funcionario.getTelefone());
		assertEquals("123.456.789-00", funcionario.getCpf());
		assertEquals(30, funcionario.getIdade());
		assertEquals("Rua A, 123", funcionario.getEndereco());
		assertEquals("Gerente", funcionario.getFuncao());
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Buscar todos os funcionários")
	void buscarTodosFuncionarios() {
		ResponseEntity<List<Funcionarios>> retorno = this.funcionariosController.findAll();
		List<Funcionarios> lista = retorno.getBody();
		HttpStatusCode status = retorno.getStatusCode();

		assertEquals(HttpStatus.OK, status);
		assertEquals(2, lista.size());
		assertEquals("Ana Costa", lista.get(0).getNome());
		assertEquals("Carlos Almeida", lista.get(1).getNome());
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Deletar funcionário com sucesso")
	void deletarFuncionarioSucesso() {
		Funcionarios funcionario = new Funcionarios();
		funcionario.setId(1L);

		ResponseEntity<String> retorno = this.funcionariosController.delete(funcionario, 1L);
		HttpStatusCode status = retorno.getStatusCode();
		String mensagem = retorno.getBody();

		assertEquals(HttpStatus.OK, status);
		assertEquals("Funcionario deletado com sucesso!", mensagem);
	}
}
