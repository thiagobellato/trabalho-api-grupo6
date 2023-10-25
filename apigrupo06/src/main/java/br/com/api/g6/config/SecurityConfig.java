package br.com.api.g6.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.g6.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	JWTFilter filter;

	@Autowired
	UserDetailsServiceImpl uds;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // Metodo encarregado de configurar a seguranca da API
		http.cors().and().csrf().disable().httpBasic().disable()
				.authorizeHttpRequests()
				.antMatchers("/categoria/salvar", "/usuario/login", "/usuario/registro", "/produto/listar",
						"/categoria/listar")
				.permitAll()
				.antMatchers("categoria/{id}", "pedido/{id}", "produto/{id}", "pedido/listar")
				.hasAnyRole("COMPRADOR", "VENDEDOR")
				.antMatchers("categoria/salvar", "categoria/delete/{id}", "categoria/atualizar/{id}", "endereco/{id}",
						"endereco/listar", "produto/salvar", "produto/delete/{id}", "produto/atualizar/{id}", "usuario/{id}",
						"usuario/listar")
				.hasRole("VENDEDOR")
				.antMatchers("endereco/salvar", "endereco/delete/{id}", "endereco/atualizar/{id}", "pedido/salvar",
						"pedido/desativar/{id}", "pedido/atualizar/{id}", "usuario/atualizar/{id}",
						"usuario/desativar/{id}")
				.hasRole("COMPRADOR")
				.and()
				.userDetailsService(uds).exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
