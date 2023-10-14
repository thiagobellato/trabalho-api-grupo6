package br.com.api.g6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g6.entities.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	@Query(value="select count(*) from g6marketplace", nativeQuery = true)
	public Integer contar();

}
