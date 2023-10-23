package br.com.api.g6.dto;

import java.time.LocalDate;
import java.util.Set;

public class UsuarioResponseCadastroDTO {

	// dados do usuário
	private String cpf;
	private LocalDate dataDeNascimento;
	private String email;
	private String bairro;
	private String nome;
	private String nomeUsuario;
	private String telefonePrincipal;
	private String telefoneSecundario;
	// dados do endereço
	private String cep;
	private String complemento;
	private String complemento2;
	private String localidade;
	private String logradouro;
	private String numero;
	private String pais;
	private String uf;
	// dados da role
	private Set<String> roles;

	public UsuarioResponseCadastroDTO() {
		super();
	}

	public UsuarioResponseCadastroDTO(String cpf, LocalDate dataDeNascimento, String email, String bairro, String nome,
			String nomeUsuario, String telefonePrincipal, String telefoneSecundario, String cep, String complemento,
			String complemento2, String localidade, String logradouro, String numero, String pais, String uf,
			Set<String> roles) {
		super();
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.email = email;
		this.bairro = bairro;
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneSecundario = telefoneSecundario;
		this.cep = cep;
		this.complemento = complemento;
		this.complemento2 = complemento2;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.pais = pais;
		this.uf = uf;
		this.roles = roles;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
