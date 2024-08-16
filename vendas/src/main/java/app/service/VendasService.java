package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Clientes;
import app.entity.Produtos;
import app.entity.Vendas;
import app.repository.VendasRepository;

@Service
public class VendasService {
	@Autowired
	private VendasRepository vendasRepository;
	@Autowired
	private ProdutosService produtosService;

	public String save(Vendas venda) {
		Double total = 0.0;

		for (int i = 0; i < venda.getProdutos().size(); i++) {
			if (venda.getProdutos().get(i) != null) {
				Produtos produto = this.produtosService.findById(venda.getProdutos().get(i).getId());
				total += produto.getPreco();
			} else {
				total += venda.getProdutos().get(i).getPreco();
			}
		}

		venda.setTotal(total);

		Clientes cliente = venda.getCliente();

		if (cliente.getIdade() < 18 && total > 500.00) {
			throw new RuntimeException("Não pode comprar acima de 500 reais se o cliente for menor de 18 anos");
		}

		this.vendasRepository.save(venda);
		return "venda cadastrado com sucesso";
	}

	public Object update(Vendas vendas, long id) {
		vendas.setId(id);
		return vendasRepository.save(vendas);
	}

	public Vendas findById(long id) {
		Optional<Vendas> optional = this.vendasRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}

	public List<Vendas> findAll() {
		return vendasRepository.findAll();
	}

	public void delete(Vendas vendas, long id) {
		vendas.setId(id);
		vendasRepository.delete(vendas);
	}

	public List<Vendas> findByClienteNome(String nome) {
		return this.vendasRepository.findByClienteNomeContains(nome);
	}

	public List<Vendas> findByFuncionarioNome(String nome) {
		return this.vendasRepository.findByFuncionarioNomeContains(nome);
	}

	public List<Vendas> findAllByOrderByTotalDesc() {
		List<Vendas> venda = this.vendasRepository.findAllByOrderByTotalDesc();
		ArrayList<Vendas> lista = new ArrayList<>(10);
		for (int i = 0; i < Math.min(venda.size(), 10); i++)
			lista.add(venda.get(i));
		return lista;
	}

}
