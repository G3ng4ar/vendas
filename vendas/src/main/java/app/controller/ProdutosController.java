package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Produtos;
import app.service.ProdutosService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtosService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produtos produtos){
		try {
			this.produtosService.save(produtos);
			return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao cadastrar produto!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Produtos produtos,@PathVariable long id ){
		try {
			this.produtosService.update(produtos, id);
			return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao atualizar produto!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Produtos> findById (@RequestBody Produtos produtos, @PathVariable long id){
		try {
			Produtos produto = this.produtosService.findById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Produtos>> findAll (){
		try {
			List<Produtos> lista = this.produtosService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@RequestBody Produtos produtos, @PathVariable long id){
		try {
			this.produtosService.delete(produtos, id);
			return new ResponseEntity<>("Funcionario deletado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
