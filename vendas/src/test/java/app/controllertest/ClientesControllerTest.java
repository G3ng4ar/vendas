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

import app.controller.ClientesController;
import app.entity.Clientes;
import app.service.ClientesService;

@SpringBootTest
public class ClientesControllerTest {

	@Autowired
	ClientesController clientesController;

	@MockBean
	ClientesService clientesService;

	@BeforeEach
	void setup() {
		// Mock do save
		Clientes clienteCadastrado = new Clientes();
		clienteCadastrado.setNome("João Silva");
		clienteCadastrado.setIdade(30);
		clienteCadastrado.setEmail("joao.silva@email.com");
		clienteCadastrado.setCpf("123.456.789-00");
		clienteCadastrado.setTelefone("(11) 91234-5678");
		clienteCadastrado.setEndereco("Rua Exemplo, 123");
		clienteCadastrado.setCep("12345-678");

		Mockito.when(this.clientesService.save(Mockito.any(Clientes.class))).thenReturn(clienteCadastrado);

		// Mock da busca por faixa etária
		List<Clientes> listaClientes = new ArrayList<>();
		listaClientes.add(new Clientes(1L, "Ana Souza", "ana.souza@email.com", "(11) 98765-4321", "987.654.321-00", 25,
				"Rua Teste, 456", "98765-432", null));
		listaClientes.add(new Clientes(2L, "Carlos Almeida", "carlos.almeida@email.com", "(21) 91234-5678",
				"654.321.987-00", 30, "Avenida Exemplo, 789", "54321-098", null));

		Mockito.when(this.clientesService.findAllByIdadeBetween(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(listaClientes);
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Cadastrar cliente com sucesso")
	void cadastrarClienteSucesso() {
		Clientes cliente = new Clientes();
		cliente.setNome("João Silva");
		cliente.setIdade(30);
		cliente.setEmail("joao.silva@email.com");

		ResponseEntity<String> retorno = this.clientesController.save(cliente);
		HttpStatusCode status = retorno.getStatusCode();
		String mensagem = retorno.getBody();

		assertEquals(HttpStatus.OK, status);
		assertEquals("Cliente cadastrado com sucesso!", mensagem);
	}

	@Test
	@DisplayName("INTEGRAÇÃO - Buscar clientes por faixa etária")
	void buscarClientesPorFaixaEtaria() {
		ResponseEntity<List<Clientes>> retorno = this.clientesController.findAllByIdade(25, 35);
		List<Clientes> lista = retorno.getBody();
		HttpStatusCode status = retorno.getStatusCode();

		assertEquals(HttpStatus.OK, status);
		assertEquals(2, lista.size());
		assertEquals("Ana Souza", lista.get(0).getNome());
		assertEquals("Carlos Almeida", lista.get(1).getNome());
	}
}
