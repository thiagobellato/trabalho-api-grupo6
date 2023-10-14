package br.com.api.g6.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.g6.entities.Usuario;
import br.com.api.g6.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public Usuario salvar(Usuario objetoUsuario) {
		return usuarioRepository.save(objetoUsuario);
	}

	public Usuario acharId(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
//	
//	public void apagar(Integer id) {
//		usuarioRepository.deleteById(id);
//	}

	public void apagarLogico(Integer id) {
		Usuario objetoUsuario = acharId(id);
		if (objetoUsuario != null) {
			objetoUsuario.setAtivo(false);
			usuarioRepository.save(objetoUsuario);
		}
	}

	public Usuario atualizar(Integer id, Usuario objetoUsuario) {
		Usuario registroAntigo = acharId(id);
		if (objetoUsuario.getAtivo() != null) {
			registroAntigo.setAtivo(null);
		}
		if (objetoUsuario.getDataNascimento() != null) {
			registroAntigo.setDataNascimento(objetoUsuario.getDataNascimento());
		}
		if (objetoUsuario.getTelefonePrincipal() != null) {
			registroAntigo.setTelefonePrincipal(objetoUsuario.getTelefonePrincipal());
		}
		if (objetoUsuario.getSenha() != null) {
			registroAntigo.setSenha(objetoUsuario.getSenha());
		}
		
		registroAntigo.setId(id);
		return usuarioRepository.save(registroAntigo);
	}

}
