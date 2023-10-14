package br.com.api.g6.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Integer id;

	@Column(name = "usuario_ativo")
	private Boolean ativo;

	@Column(name = "usuario_nome")
	private String nome;

	@Column(name = "usuario_telefonePrincipal")
	private String telefonePrincipal;

	@Column(name = "usuario_nomeUsuario")
	private String nomeUsuario;

	@Column(name = "usuario_senha")
	private String senha;

	@Column(name = "usuario_email")
	private String email;

	@Column(name = "usuario_cpf")
	private String cpf;

	@Column(name = "usuario_dataNascimento")
	private Date dataNascimento;

	@OneToMany
	@JoinColumn(name = "produto_id")
	private List<Produto> produtos;

	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Endereco> enderecos;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer id, Boolean ativo, String nome, String telefonePrincipal, String nomeUsuario, String senha,
			String email, String cpf, Date dataNascimento, List<Produto> produtos, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.nome = nome;
		this.telefonePrincipal = telefonePrincipal;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.produtos = produtos;
		this.enderecos = enderecos;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", ativo=" + ativo + ", nome=" + nome + ", telefonePrincipal=" + telefonePrincipal
				+ ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + ", email=" + email + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + ", produtos=" + produtos + ", enderecos=" + enderecos + "]";
	}

}
