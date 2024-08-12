package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produtos;
import app.entity.Vendas;
import app.repository.VendasRepository;

@Service
public class VendasService {
	@Autowired
	private VendasRepository vendasRepository;
	private ProdutosService produtosService;
	private Produtos produto;
	
	public Object save (Vendas vendas) {
		Double total=0.0;
		for (var i : vendas.getProdutos()) {
			produto = this.produtosService.findById(i.getId());
			total+= produto.getPreco();
		}
		vendas.setTotal(total);
		this.vendasRepository.save(vendas);
		return "Venda realizada com sucesso!";
	}
	
	public Object update (Vendas vendas, long id) {
		vendas.setId(id);
		return vendasRepository.save(vendas);
	}
	
	public Vendas findById (long id) {
		Optional<Vendas> optional = this.vendasRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else 
			return null;
	}
	
	public List<Vendas> findAll () {
		return vendasRepository.findAll();
	}
	
	public void delete (Vendas vendas, long id) {
		vendas.setId(id);
		vendasRepository.delete(vendas);
	}
}
