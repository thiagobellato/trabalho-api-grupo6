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
		http.cors()
				.and()
				.csrf()
				.disable()
				.httpBasic()
				.disable()
				.authorizeHttpRequests()
				/* PERMISSÃO TOTAL CATEGORIA */
				.antMatchers(
						"/categoria/count",
						"/endereco/count",
						"/pedido/count",
						"/produto/count",
						"/usuario/count")
				.permitAll()
				/* ACESSOS NA ENTIDADE CATEGORIA */
				.antMatchers(
						"/categoria/")
				.hasRole("VENDEDOR")
				.antMatchers(
						"/categoria/")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE ENDEREÇO */
				.antMatchers("/endereco/")
				.hasRole("VENDEDOR")
				.antMatchers("/endereco/")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE PEDIDO */
				.antMatchers("/pedido/")
				.hasRole("VENDEDOR")
				.antMatchers("/pedido/")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE PRODUTO */
				.antMatchers("/produto/")
				.hasRole("VENDEDOR")
				.antMatchers("/produto/")
				.hasRole("COMPRADOR")
				/* ACESSOS NA ENTIDADE USUARIO */
				.antMatchers("/usuario/")
				.hasRole("VENDEDOR")
				.antMatchers("/usuario/")
				.hasRole("COMPRADOR")
				.and()
				.userDetailsService(uds)
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
