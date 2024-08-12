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

import app.entity.Funcionarios;
import app.service.FuncionariosService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionariosController {
	@Autowired
	private FuncionariosService funcionariosService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionarios funcionarios){
		try {
			this.funcionariosService.save(funcionarios);
			return new ResponseEntity<>("Funcionario cadastrado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao cadastrar funcionario!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Funcionarios funcionarios,@PathVariable long id ){
		try {
			this.funcionariosService.update(funcionarios, id);
			return new ResponseEntity<>("Funcionario atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Falha ao atualizar funcionario!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Funcionarios> findById (@RequestBody Funcionarios funcionarios, @PathVariable long id){
		try {
			Funcionarios funcionario = this.funcionariosService.findById(id);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Funcionarios>> findAll (){
		try {
			List<Funcionarios> lista = this.funcionariosService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@RequestBody Funcionarios funcionarios, @PathVariable long id){
		try {
			this.funcionariosService.delete(funcionarios, id);
			return new ResponseEntity<>("Funcionario deletado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
