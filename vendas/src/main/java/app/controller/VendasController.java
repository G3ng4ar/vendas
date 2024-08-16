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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Vendas;
import app.service.VendasService;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {

	@Autowired
	private VendasService vendasService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Vendas vendas){
		try {
			this.vendasService.save(vendas);
			return new ResponseEntity<>("Venda realizada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao realizar venda!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Vendas vendas,@PathVariable long id ){
		try {
			this.vendasService.update(vendas, id);
			return new ResponseEntity<>("Venda atualizada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao atualizar venda!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Vendas> findById (@RequestBody Vendas vendas, @PathVariable long id){
		try {	
			Vendas venda = this.vendasService.findById(id);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Vendas>> findAll (){
		try {
			List<Vendas> lista = this.vendasService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@RequestBody Vendas vendas, @PathVariable long id){
		try {
			this.vendasService.delete(vendas, id);
			return new ResponseEntity<>("Funcionario deletado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByCliente")
	public ResponseEntity<List<Vendas>> findByClienteNome(@RequestParam String nome) {
		try {
			List<Vendas> venda = this.vendasService.findByClienteNome(nome);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByFuncionario")
	public ResponseEntity<List<Vendas>> findByFuncionarioNome(@RequestParam String nome) {
		try {
			List<Vendas> venda = this.vendasService.findByFuncionarioNome(nome);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAllByTotal")
	public ResponseEntity<List<Vendas>> findAllByOrderByTotalDesc() {
		try {
			List<Vendas> venda = this.vendasService.findAllByOrderByTotalDesc();
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
