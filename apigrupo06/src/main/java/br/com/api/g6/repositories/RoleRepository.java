package br.com.api.g6.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g6.entities.Role;
import br.com.api.g6.enums.TipoRoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(TipoRoleEnum roleUser);

	@Query(value = "select r.name from usuario u\r\n" + //
			"inner join usuario_role ur on u.id_usuario = ur.id_usuario \r\n" + //
			"inner join roles r on ur.role_id = r.id\r\n" + //
			"where u.email_usuario  = :email", nativeQuery = true)
	Set<String> buscarRolesPorUsuario(String email);
}
