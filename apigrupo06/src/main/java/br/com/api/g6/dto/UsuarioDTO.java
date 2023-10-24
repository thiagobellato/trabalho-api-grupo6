package br.com.api.g6.dto;

import java.time.LocalDate;

public class UsuarioDTO {

	private Boolean ativo;
	private String nome;
	private String telefonePrincipal;
	private String telefoneSecundario;
	private String nomeUsuario;
	private String password;
	private String email;
	private String cpf;
	private LocalDate dataDeNascimento;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Boolean ativo, String nome, String telefonePrincipal, String telefoneSecundario,
			String nomeUsuario, String password, String email, String cpf, LocalDate dataDeNascimento) {
		super();
		this.ativo = ativo;
		this.nome = nome;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneSecundario = telefoneSecundario;
		this.nomeUsuario = nomeUsuario;
		this.password = password;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
}
