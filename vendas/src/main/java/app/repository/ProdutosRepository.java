package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{

}
