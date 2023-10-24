package br.com.api.g6.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.g6.entities.Usuario;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {

	@Value("${jwt-secret}")
	private String secret;

	@Value("${jwt-subject}")
	private String subject;

	@Value("${jwt-company-project-name}")
	private String companyProjectName;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
		return JWT.create().withSubject(subject).withClaim("email", email).withIssuedAt(new Date())
				.withIssuer(companyProjectName).sign(Algorithm.HMAC256(secret));
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String generateTokenWithUsuarioData(Usuario usuario) throws IllegalArgumentException, JWTCreationException {
		ObjectMapper mapper = new ObjectMapper();
		String usuarioJson = null;
		try {
			usuarioJson = mapper.writeValueAsString(usuario);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return JWT.create().withSubject(subject).withClaim("usuario", usuarioJson).withIssuedAt(new Date())
				.withIssuer(companyProjectName).sign(Algorithm.HMAC256(secret));
	}

	public String validateTokenAndRetrieveSubject(String token) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject(subject)
				.withIssuer(companyProjectName).build();
		DecodedJWT jwt = verifier.verify(token);

		// Pegando o email dentro da chave usuario (string json que contem os dados do
		// usuario)
		Usuario usuario = new Usuario();
		try {
			usuario = mapper.readValue(jwt.getClaim("usuario").asString(), Usuario.class);
		} catch (JsonProcessingException e) {
			throw new Exception("Ocorreu um erro e nao foi possivel converter o usario a partir da string json - " + e);
		}
		return usuario.getEmail();
	}
}
