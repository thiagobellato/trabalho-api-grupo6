package br.com.api.g6.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.api.g6.dto.UsuarioRequestCadastroDTO;
import br.com.api.g6.dto.UsuarioResponseCadastroDTO;
import br.com.api.g6.entities.Endereco;
import br.com.api.g6.entities.Role;
import br.com.api.g6.entities.Usuario;
import br.com.api.g6.enums.TipoRoleEnum;
import br.com.api.g6.repositories.EnderecoRepository;
import br.com.api.g6.repositories.RoleRepository;
import br.com.api.g6.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	RoleService roleService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioResponseCadastroDTO acharId(Integer id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		UsuarioResponseCadastroDTO dtoUsuario = converterUsuarioDTO(usuario);
		return dtoUsuario;
	}

	public Usuario acharId2(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public void apagarLogico(Integer id) {
		Usuario objetoUsuario = acharId2(id);

		if (objetoUsuario != null) {
			objetoUsuario.setAtivo(false);
			usuarioRepository.save(objetoUsuario);
		}
	}

	public UsuarioRequestCadastroDTO atualizar(Integer id,
			UsuarioRequestCadastroDTO objetoUsuario) {
		Usuario registroAntigo = acharId2(id);

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
		usuarioRepository.save(registroAntigo);

		return objetoUsuario;
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).get();
	}

	public void save(UsuarioRequestCadastroDTO usuario) {
		// Estamos pegando as roles (funções) do objeto UserDTO e inicializando um
		// conjunto de roles Set<Role>.
		Set<String> strRoles = usuario.getRoles();
		Set<Role> roles = new HashSet<>();

		// Aqui, estamos verificando se as roles fornecidas estão vazias. Se estiverem
		// vazias, adicionamos a role de COMPRADOR. Se não estiverem vazias, iteramos
		// sobre as roles e adicionamos as roles correspondentes ao conjunto roles.

		// Se a lista de strings de roles (strRoles) for nula, isso significa que o
		// usuário não especificou nenhuma role. Nesse caso, o código encontra a role de
		// ROLE_COMPRADOR no repositório (roleRepository) e a adiciona ao conjunto de
		// roles (roles).
		if (strRoles == null) {
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
			// Se strRoles não for nulo, o código itera sobre as roles fornecidas. Se a
			// string de role for "VENDEDOR", ele encontra a ROLE_VENDEDOR no repositório e
			// a adiciona ao conjunto roles. Se a string de role for "COMPRADOR", ele
			// encontra a ROLE_COMPRADOR no repositório e a adiciona ao conjunto roles.
			strRoles.forEach(role -> {
				switch (role) {
					case "VENDEDOR":
						Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_VENDEDOR)
								.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
						roles.add(adminRole);
						break;
					case "COMPRADOR":
						Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
								.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
						roles.add(usuarioRole);
				}
			});
		}

		// Estamos usando o serviço enderecoService para pesquisar informações de
		// endereço com base no CEP fornecido pelo usuário.
		Endereco viaCep = enderecoService.pesquisarEndereco(usuario.getCep());

		// Aqui, estamos criando um novo objeto Endereco com as informações do CEP
		// pesquisado e salvando-o no repositório enderecoRepository.
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(usuario.getCep());
		enderecoNovo.setComplemento(viaCep.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setNumero(usuario.getNumero());
		enderecoNovo.setPais(usuario.getPais());
		enderecoNovo.setComplemento2(usuario.getComplemento2());
		enderecoService.salvarEnderecoCadastroUsuario(enderecoNovo);

		// Estamos criando um novo objeto Usuario com as informações fornecidas pelo
		// usuário e a senha é criptografada antes de ser armazenada.
		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		usuarioResumido.setCpf(usuario.getCpf());
		usuarioResumido.setDataDeNascimento(usuario.getDataDeNascimento());
		usuarioResumido.setNome(usuario.getNome());
		usuarioResumido.setTelefonePrincipal(usuario.getTelefonePrincipal());
		usuarioResumido.setTelefoneSecundario(usuario.getTelefoneSecundario());
		usuarioResumido.setAtivo(true);

		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);

		List<Endereco> enderecosUsuario = new ArrayList<>();
		enderecosUsuario.add(enderecoNovo);
		usuarioResumido.setEnderecos(enderecosUsuario);

		usuarioRepository.save(usuarioResumido);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	public UsuarioResponseCadastroDTO converterUsuarioDTO(Usuario usuario) {
		UsuarioResponseCadastroDTO usuarioConvertido = new UsuarioResponseCadastroDTO();

		usuarioConvertido.setCpf(usuario.getCpf());
		usuarioConvertido.setDataDeNascimento(usuario.getDataDeNascimento());
		usuarioConvertido.setEmail(usuario.getEmail());
		usuarioConvertido.setBairro(usuario.getBairro());
		usuarioConvertido.setNome(usuario.getNome());
		usuarioConvertido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioConvertido.setTelefonePrincipal(usuario.getTelefonePrincipal());
		usuarioConvertido.setTelefoneSecundario(usuario.getTelefoneSecundario());

		return usuarioConvertido;
	}
}
