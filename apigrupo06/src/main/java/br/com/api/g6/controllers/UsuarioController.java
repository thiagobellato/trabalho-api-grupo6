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

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

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
		usuarioService.apagarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetoUsuario) {
		return usuarioService.atualizar(id, objetoUsuario);
	}
	
	@PostMapping("/registro")
	public Usuario cadastro(@RequestParam String email, @RequestBody UserDTO usuario) {

		Set<String> strRoles = usuario.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
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

		Endereco viaCep = enderecoService.pesquisarEndereco(usuario.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(usuario.getCep());
		enderecoNovo.setComplemento(viaCep.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setNumero(usuario.getNumero());
		enderecoRepository.save(enderecoNovo);

		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);

//		emailService.envioEmailCadastro(email, usuario);

		return usuarioService.save(usuarioResumido);
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authManager.authenticate(authInputToken);

			Usuario usuario = usuarioService.findByEmail(body.getEmail());
			Usuario usuarioResumido = new Usuario();
			usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
			usuarioResumido.setEmail(usuario.getEmail());
			usuarioResumido.setIdUser(usuario.getIdUser());
			usuarioResumido.setRoles(usuario.getRoles());
			String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);

			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}
}
