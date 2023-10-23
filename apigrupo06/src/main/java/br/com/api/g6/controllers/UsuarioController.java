package br.com.api.g6.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.api.g6.dto.UsuarioRequestCadastroDTO;
import br.com.api.g6.entities.Usuario;
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
		EmailService.envioEmailContaDesativada(null);
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
	public ResponseEntity<String> cadastro(@RequestParam String email, @RequestBody UsuarioRequestCadastroDTO usuario) {
		// responsável pelo envio de um e-mail de confirmação ou notificação após o
		// registro.
		// emailService.envioEmailCadastro(email, usuario);

		// estamos chamando o método save do serviço usuarioService para salvar o objeto
		// usuarioResumido no banco de dados e retorná-lo como resposta à requisição.
		
		//return usuarioService.save(usuario);
		usuarioService.save(usuario);
		return ResponseEntity.ok("Cadastro realizado com sucesso!");
	}

	// Este é um endpoint POST que lida com solicitações de login. Ele espera um
	// corpo de requisição (body) que é mapeado para um objeto LoginDTO.
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			EmailService.envioEmailContaDesativada(null);
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
