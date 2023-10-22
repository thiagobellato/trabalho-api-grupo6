package br.com.api.g6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g6.dto.UserDTO;
import br.com.api.g6.entities.Endereco;
import br.com.api.g6.entities.Role;
import br.com.api.g6.entities.Usuario;
import br.com.api.g6.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public Usuario salvar(UserDTO objetoUsuario) {
		Usuario usuarioNovo = new Usuario();

		usuarioNovo.setAtivo(objetoUsuario.getUsuarioDTO().getAtivo());
		usuarioNovo.setNome(objetoUsuario.getUsuarioDTO().getNome());
		usuarioNovo.setTelefonePrincipal(objetoUsuario.getUsuarioDTO().getTelefonePrincipal());
		usuarioNovo.setTelefoneSecundario(objetoUsuario.getUsuarioDTO().getTelefoneSecundario());
		usuarioNovo.setNomeUsuario(objetoUsuario.getUsuarioDTO().getNomeUsuario());
		usuarioNovo.setPassword(objetoUsuario.getUsuarioDTO().getPassword());
		usuarioNovo.setEmail(objetoUsuario.getUsuarioDTO().getEmail());
		usuarioNovo.setCpf(objetoUsuario.getUsuarioDTO().getCpf());
		usuarioNovo.setDataDeNascimento(objetoUsuario.getUsuarioDTO().getDataDeNascimento());
		
		Endereco enderecoNovo = new Endereco();
		
		enderecoNovo.setCep(objetoUsuario.getEnderecoDTO().getCep());
		enderecoNovo.setComplemento2(objetoUsuario.getEnderecoDTO().getComplemento2());
		enderecoNovo.setNumero(objetoUsuario.getEnderecoDTO().getNumero());
		enderecoNovo.setPais(objetoUsuario.getEnderecoDTO().getPais());
		
		Role roleNovo = new Role();
		
		roleNovo.setName(objetoUsuario.getRole().getName());
		
		return usuarioRepository.save(usuarioNovo);
	}

	public Usuario acharId(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

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

		if (objetoUsuario.getDataDeNascimento() != null) {
			registroAntigo.setDataDeNascimento(objetoUsuario.getDataDeNascimento());
		}

		if (objetoUsuario.getTelefonePrincipal() != null) {
			registroAntigo.setTelefonePrincipal(objetoUsuario.getTelefonePrincipal());
		}

		if (objetoUsuario.getTelefoneSecundario() != null) {
			registroAntigo.setTelefoneSecundario(objetoUsuario.getTelefoneSecundario());
		}

		if (objetoUsuario.getPassword() != null) {
			registroAntigo.setPassword(objetoUsuario.getPassword());
		}

		registroAntigo.setIdUser(id);
		return usuarioRepository.save(registroAntigo);
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).get();
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
}
