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

import app.entity.Clientes;
import app.service.ClientesService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

	@Autowired
	private ClientesService clientesService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Clientes clientes){
		try {
			this.clientesService.save(clientes);
			return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao cadastrar cliente!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Clientes clientes,@PathVariable long id ){
		try {
			this.clientesService.update(clientes, id);
			return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao atualizar cliente!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Clientes> findById (@RequestBody Clientes clientes, @PathVariable long id){
		try {
			Clientes cliente = this.clientesService.findById(id);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Clientes>> findAll (){
		try {
			List<Clientes> lista = this.clientesService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@RequestBody Clientes clientes, @PathVariable long id){
		try {
			this.clientesService.delete(clientes, id);
			return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAllByIdade")
	public ResponseEntity<List<Clientes>> findAllByIdade(@RequestParam int idadeMin, @RequestParam int idadeMax) {
		try {
			List<Clientes> cliente = this.clientesService.findAllByIdadeBetween(idadeMin, idadeMax);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
