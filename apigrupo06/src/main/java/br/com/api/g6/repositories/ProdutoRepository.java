package br.com.api.g6.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g6.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "select count(*) from produto", nativeQuery = true)
	public Integer contar();

	@Query(value = "select * from produto where nome_produto = :nome", nativeQuery = true)
	public Produto findByName(String nome);

	@Query(value = "select * from pedido_finalizado p where pedido_id = :idPedido", nativeQuery = true)
	public List<Produto> listarProdutosPorPedido(Integer id);
}
