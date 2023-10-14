package br.com.api.g6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.api.g6.entities.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
	
	@Query(value="select count(*) from g6marketplace", nativeQuery = true)
	public Integer contar();
}
