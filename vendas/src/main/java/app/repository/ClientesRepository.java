package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Clientes;


public interface ClientesRepository extends JpaRepository<Clientes, Long>{
	public List<Clientes> findAllByIdadeBetween(int idadeMin, int idadeMax);

}
