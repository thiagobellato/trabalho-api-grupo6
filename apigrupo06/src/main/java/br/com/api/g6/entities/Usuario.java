package br.com.api.g6.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	@AssertTrue /* diz que o boolean começa ativo */
	@Column(name = "ativo_usuario")
	private Boolean ativo;

	@NotNull
	@Column(name = "nome_usuario")
	private String nome;

	@NotNull
	@Column(name = "telefone_principal_usuario")
	private String telefonePrincipal;

	@Column(name = "telefone_secundario_usuario")
	private String telefoneSecundario;

	@NotNull
	@Column(name = "login_usuario")
	private String login;

	@Size(min = 6, max = 15)
	@NotNull
	@Column(name = "senha_usuario")
	private String senha;

	@Email
	@NotNull
	@Column(name = "email_usuario")
	private String email;

	@NotNull
	@Size(max = 11, min = 11)
	@Column(name = "cpf_usuario")
	private String cpf;

	@NotNull
	@Column(name = "data_de_nascimento_usuario")
	private LocalDate dataDeNascimento;

	@OneToMany
	@JoinColumn(name = "id_usuario")
	private List<Produto> produtos;

	@OneToMany
	@JoinColumn(name = "id_usuario")
	private List<Endereco> enderecos;

	@OneToMany
	@JoinColumn(name = "id_usuario")
	private List<Pedido> pedidos;

	public Usuario() {
		super();
	}

	public Usuario(Integer id, Boolean ativo, String nome, String telefonePrincipal, String telefoneSecundario,
			String login, String senha, String email, String cpf, LocalDate dataDeNascimento, List<Produto> produtos,
			List<Endereco> enderecos, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.nome = nome;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneSecundario = telefoneSecundario;
		this.login = login; /* PRECISA TIRAR? */
		this.senha = senha; /* PRECISA TIRAR? */
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.produtos = produtos;
		this.enderecos = enderecos;
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {

		return "Usuario [id = " + id + ", ativo = " + ativo + ", nome = " + nome + ", telefonePrincipal = "
				+ telefonePrincipal
				+ ", telefoneSecundario = " + telefoneSecundario + ", login = " + login + ", senha = " + senha
				+ ", email = "
				+ email + ", cpf=" + cpf + ", dataDeNascimento = " + dataDeNascimento + ", produtos = " + produtos
				+ ", enderecos = " + enderecos + ", pedidos = " + pedidos + "]";
	}
}
