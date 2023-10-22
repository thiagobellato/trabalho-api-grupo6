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
	protected void configure(HttpSecurity http) throws Exception { // Metodo encarregado de configurar a seguranca da
																	// API
		http.cors().and().csrf().disable().httpBasic().disable().authorizeHttpRequests()
				/* PERMISSÃO TOTAL CATEGORIA */
				.antMatchers("/categoria/count", "/categoria/{id}", "/categoria/listar", "/pedido/{id}",
						"/pedido/listar", "/produto/listar", "/produto/listar", "/produto/count", "/produto/{id}",
						"/usuario/registro", "/usuario/login", "/usuario/count", "/categoria/delete/{id}",
						"/categoria/atualizar/{id}","/usuario/salvar")
				.permitAll()
				/* ACESSOS NA ENTIDADE CATEGORIA */
				.antMatchers("/categoria/salvar/{id}").hasRole("VENDEDOR")
				/* ACESSOS NA ENTIDADE ENDEREÇO */
				.antMatchers("endereco/count", "/endereco/{id}", "/endereco/listar").hasRole("VENDEDOR")
				.antMatchers("endereco/count", "endereco/listar", "endereco/delete/{id}", "endereco/atualizar/{id}",
						"/endereco/salvar")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE PEDIDO */
				.antMatchers("pedido/count").hasRole("VENDEDOR")
				.antMatchers("pedido/count", "pedido/salvar", "pedido/desativar/{id}", "pedido/atualizar/{id}")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE PRODUTO */
				.antMatchers("produto/salvar", "produto/delete/{id}", "produto/atualizar/{id}").hasRole("VENDEDOR")
				.antMatchers("/produto/").hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE USUARIO */
				.antMatchers("usuario/count", "usuario/{id}", "usuario/listar").hasRole("VENDEDOR")
				.antMatchers("usuario/atualizar/{id}", "usuario/desativar/{id}").hasRole("COMPRADOR").and()
				.userDetailsService(uds).exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
