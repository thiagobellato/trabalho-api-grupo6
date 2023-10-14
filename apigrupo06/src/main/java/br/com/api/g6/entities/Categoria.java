package br.com.api.g6.entities;

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
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id")
	private Integer id;

	@Column(name = "categoria_nome")
	private String nome;

	@Column(name = "categoria_descricao")
	private String descricao;
	
	@OneToMany
	@JoinColumn(name = "categoria_id")
	private List<Produto> produtos;

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer id, String nome, String descricao, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", produtos=" + produtos + "]";
	}

	
}
