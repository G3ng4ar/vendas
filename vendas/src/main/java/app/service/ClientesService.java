package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Clientes;
import app.repository.ClientesRepository;

@Service

public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	public Object save (Clientes clientes) {
		return clientesRepository.save(clientes);
	}
	
	public Object update (Clientes clientes, long id) {
		clientes.setId(id);
		return clientesRepository.save(clientes);
	}
	
	public Clientes findById (long id) {
		
		Optional<Clientes> optional = this.clientesRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else
			return null;
		
	}
	
	public List<Clientes> findAll () {
		
		return clientesRepository.findAll();
	}
	
	public void delete (Clientes clientes,long id) {
		
		clientes.setId(id);
		clientesRepository.delete(clientes);
	}
	
}
