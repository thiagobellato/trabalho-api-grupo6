package br.com.api.g6.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
	private Integer id;

	@Column(name = "prod_nome")
	private String nome;

	@Column(name = "prod_descricao")
	private String descricao;

	@Column(name = "prod_data_de_fabricacao")
	private Date data_de_fabricacao;

	@Column(name = "prod_qntd")
	private Integer qntd_produto;

	@Column(name = "prod_valor_unitario")
	private Double valor_unitario;

	@OneToMany
	@JoinColumn(name = "categoria_id")
	private List<Categoria> categorias;
	//OneToMany/ManyToOne????

	@ManyToMany
	@JoinTable(name="pedido_produto",
	joinColumns=@JoinColumn(name="produto_id"),
	inverseJoinColumns=@JoinColumn(name="pedido_id"))
		
	private List<Pedido> pedidos;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer id, String nome, String descricao, Date data_de_fabricacao, Integer qntd_produto,
			Double valor_unitario, List<Categoria> categorias, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.data_de_fabricacao = data_de_fabricacao;
		this.qntd_produto = qntd_produto;
		this.valor_unitario = valor_unitario;
		this.categorias = categorias;
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_de_fabricacao() {
		return data_de_fabricacao;
	}

	public void setData_de_fabricacao(Date data_de_fabricacao) {
		this.data_de_fabricacao = data_de_fabricacao;
	}

	public Integer getQntd_produto() {
		return qntd_produto;
	}

	public void setQntd_produto(Integer qntd_produto) {
		this.qntd_produto = qntd_produto;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", data_de_fabricacao="
				+ data_de_fabricacao + ", qntd_produto=" + qntd_produto + ", valor_unitario=" + valor_unitario
				+ ", categorias=" + categorias + ", pedidos=" + pedidos + "]";
	}
	
	
}
