package br.com.api.g6.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g6.entities.Role;
import br.com.api.g6.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public Set<String> buscarRolesPorUsuario(String email) {
		return roleRepository.buscarRolesPorUsuario(email);
	}
}
