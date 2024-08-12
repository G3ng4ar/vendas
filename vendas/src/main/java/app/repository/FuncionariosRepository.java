package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Long>{

}
