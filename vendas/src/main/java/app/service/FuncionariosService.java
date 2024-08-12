package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionarios;
import app.repository.FuncionariosRepository;

@Service
public class FuncionariosService {
	
	@Autowired
	private FuncionariosRepository funcionariosRepository;
	
	public Object save (Funcionarios funcionarios) {
		return funcionariosRepository.save(funcionarios);
	}
	
	public Object update (Funcionarios funcionarios, long id) {
		funcionarios.setId(id);
		return funcionariosRepository.save(funcionarios);
	}
	
	public Funcionarios findById (long id) {
		Optional<Funcionarios> optional = this.funcionariosRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else
			return null;
	}
	
	public List<Funcionarios> findAll (){
		return funcionariosRepository.findAll();
	}
	
	public void delete (Funcionarios funcionarios, long id) {
		funcionarios.setId(id);
		funcionariosRepository.delete(funcionarios);
	}
}
