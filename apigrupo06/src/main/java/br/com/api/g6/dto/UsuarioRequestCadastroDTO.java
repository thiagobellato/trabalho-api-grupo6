package br.com.api.g6.dto;

import java.time.LocalDate;
import java.util.Set;

public class UsuarioRequestCadastroDTO {

	private String nomeUsuario;
	private String email;
	private Set<String> roles;
	private String password;
	private LocalDate dataDeNascimento;
	private String cpf;
	private String telefonePrincipal;
	private String telefoneSecundario;
	private String nome;
	private String cep;
	private String numero;
	private String pais;
	private String complemento2;

	public UsuarioRequestCadastroDTO() {
		super();
	}

	public UsuarioRequestCadastroDTO(String nomeUsuario, String email, Set<String> roles, String password, LocalDate dataDeNascimento,
			String cpf, String telefonePrincipal, String telefoneSecundario, String nome, String cep, String numero, String pais,
			String complemento2) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.roles = roles;
		this.password = password;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneSecundario = telefoneSecundario;
		this.nome = nome;
		this.cep = cep;
		this.numero = numero;
		this.pais = pais;
		this.complemento2 = complemento2;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(String telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

   public Object getAtivo() {
      return null;
   }
}