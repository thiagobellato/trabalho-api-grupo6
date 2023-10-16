package br.com.api.g6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.api.g6.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "select count(*) from produto", nativeQuery = true)
	public Integer contar();

	@Query(value = "select * from produto where nome_produto = ?1", nativeQuery = true)
	public Produto findByName(String nome);
}
