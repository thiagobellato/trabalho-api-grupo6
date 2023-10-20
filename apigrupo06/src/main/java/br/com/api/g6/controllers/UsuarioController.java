package br.com.api.g6.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g6.config.JWTUtil;
import br.com.api.g6.dto.LoginDTO;
import br.com.api.g6.dto.UserDTO;
import br.com.api.g6.entities.Endereco;
import br.com.api.g6.entities.Role;
import br.com.api.g6.entities.Usuario;
import br.com.api.g6.enums.TipoRoleEnum;
import br.com.api.g6.repositories.EnderecoRepository;
import br.com.api.g6.repositories.RoleRepository;
import br.com.api.g6.services.EmailService;
import br.com.api.g6.services.EnderecoService;
import br.com.api.g6.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	RoleRepository roleRepository;

	// Você está injetando uma instância de JWTUtil para manipular tokens JWT.
	@Autowired
	private JWTUtil jwtUtil;

	// Você está injetando um AuthenticationManager para gerenciar a autenticação do
	// usuário.
	@Autowired
	private AuthenticationManager authManager;

	// Você está injetando um codificador de senhas para criptografar senhas.
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/count")
	public Integer getCount() {
		return usuarioService.getCount();
	}

	@PostMapping("/salvar")
	public Usuario salvar(@RequestBody Usuario objetoUsuario) {
		return usuarioService.salvar(objetoUsuario);
	}

	@GetMapping("/{id}")
	public Usuario acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@DeleteMapping("/desativar/{id}")
	public void apagarLogico(@PathVariable Integer id) {
		EmailService.envioEmailContaDesativada();
		usuarioService.apagarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetoUsuario) {
		return usuarioService.atualizar(id, objetoUsuario);
	}
	
	// Aqui estamos criando um método POST que responde a requisições feitas para o
	// endpoint /registro. Ele espera um parâmetro email como um parâmetro da URL e
	// um corpo de requisição (body) que é mapeado para um objeto UserDTO.
	@PostMapping("/registro")
	public Usuario cadastro(@RequestParam String email, @RequestBody UserDTO usuario) {

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
		enderecoRepository.save(enderecoNovo);

		// Estamos criando um novo objeto Usuario com as informações fornecidas pelo
		// usuário e a senha é criptografada antes de ser armazenada.
		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);

		// responsável pelo envio de um e-mail de confirmação ou notificação após o
		// registro.
		// emailService.envioEmailCadastro(email, usuario);

		// estamos chamando o método save do serviço usuarioService para salvar o objeto
		// usuarioResumido no banco de dados e retorná-lo como resposta à requisição.
		return usuarioService.save(usuarioResumido);
	}

	// Este é um endpoint POST que lida com solicitações de login. Ele espera um
	// corpo de requisição (body) que é mapeado para um objeto LoginDTO.
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			// Aqui, você está criando um UsernamePasswordAuthenticationToken com as
			// credenciais fornecidas no corpo da requisição (e-mail e senha).
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			// O authManager está sendo usado para autenticar o token criado anteriormente.
			// Se as credenciais não forem válidas, uma AuthenticationException será
			// lançada.
			authManager.authenticate(authInputToken);

			// Você está usando o serviço usuarioService para encontrar um usuário pelo
			// e-mail fornecido na requisição.
			Usuario usuario = usuarioService.findByEmail(body.getEmail());

			// Você está criando um novo objeto Usuario que representa o usuário
			// autenticado, mas com informações resumidas (nome de usuário, e-mail, ID e
			// roles).
			Usuario usuarioResumido = new Usuario();
			usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
			usuarioResumido.setEmail(usuario.getEmail());
			usuarioResumido.setIdUser(usuario.getIdUser());
			usuarioResumido.setRoles(usuario.getRoles());

			// Você está gerando um token JWT usando o objeto usuarioResumido e a classe
			// jwtUtil. O token geralmente é usado para autenticação subsequente.
			String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);

			// Finalmente, você está retornando um mapa contendo o token JWT com a chave
			// "jwt-token". Isso será enviado de volta ao cliente após o login bem-sucedido.
			return Collections.singletonMap("jwt-token", token);

		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}
}
