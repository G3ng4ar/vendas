package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

	public List<Vendas> findByClienteNomeContains(String nome);

	public List<Vendas> findByFuncionarioNomeContains(String nome);

	public List<Vendas> findAllByOrderByTotalDesc();

}
