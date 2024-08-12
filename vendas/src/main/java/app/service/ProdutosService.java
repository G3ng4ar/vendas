package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produtos;
import app.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	public Object save (Produtos produtos) {
		return produtosRepository.save(produtos);
	}
	
	public Object update (Produtos produtos, long id) {
		produtos.setId(id);
		return produtosRepository.save(produtos);
	}
	
	public Produtos findById (long id) {
		Optional<Produtos> optional = this.produtosRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else 
			return null;
	}
	
	public List<Produtos> findAll () {
		return produtosRepository.findAll();
	}
	
	public void delete (Produtos produtos, long id) {
		produtos.setId(id);
		produtosRepository.delete(produtos);
	}
}
