package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long>{

}
