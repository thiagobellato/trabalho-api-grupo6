package br.com.api.g6.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUser;

	@AssertTrue

	@Column(name = "ativo_usuario")
	private Boolean ativo;

	@Column(name = "nome_completo_usuario")

	private String nome;

	@Column(name = "telefone_principal_usuario")
	private String telefonePrincipal;

	@Column(name = "telefone_secundario_usuario")
	private String telefoneSecundario;

	@Column(name = "login_usuario")
	private String nomeUsuario;

	@Column(name = "senha_usuario")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Email
	@Column(name = "email_usuario")
	private String email;

	@Size(min = 14, max = 14)
	@CPF
	@NotNull(message = "CPF não pode ser nulo")
	@NotBlank(message = "CPF não pode ser vazio")

	@Column(name = "cpf_usuario")
	private String cpf;

	@Column(name = "data_de_nascimento_usuario")
	private LocalDate dataDeNascimento;

	@ManyToMany
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

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
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer idUser, Boolean ativo, String nome, String telefonePrincipal, String telefoneSecundario,
			String nomeUsuario, String password, @Email String email, String cpf, LocalDate dataDeNascimento,
			Set<Role> roles, List<Produto> produtos, List<Endereco> enderecos, List<Pedido> pedidos) {
		super();

		this.idUser = idUser;
		this.ativo = ativo;
		this.nome = nome;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneSecundario = telefoneSecundario;
		this.nomeUsuario = nomeUsuario;
		this.password = password;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.roles = roles;
		this.produtos = produtos;
		this.enderecos = enderecos;
		this.pedidos = pedidos;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

}
