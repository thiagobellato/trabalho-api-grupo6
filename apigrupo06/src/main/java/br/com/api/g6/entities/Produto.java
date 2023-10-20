package br.com.api.g6.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id;

	@NotNull
	@Column(name = "nome_produto")
	private String nome;

	@NotNull
	@Size(max = 100)
	@Column(name = "descricao_produto")
	private String descricao;

	@NotNull
	@Column(name = "data_de_fabricacao_produto")
	private LocalDate dataDeFabricacao;

	@NotNull
	@Column(name = "qntd_produto")
	private Integer quantidade;

	@NotNull
	@Column(name = "valor_unitario_produto")
	private Double valorUnitario;

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, String descricao, LocalDate dataDeFabricacao, Integer quantidade,
			Double valorUnitario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataDeFabricacao = dataDeFabricacao;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public  String getNome() {
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

	public LocalDate getDataDeFabricacao() {
		return dataDeFabricacao;
	}

	public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
		this.dataDeFabricacao = dataDeFabricacao;
	}

	public  Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public  Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public String toString() {
		return "Produto [id = " + id + ", nome = " + nome + ", descricao = " + descricao + ", dataDeFabricacao = "
				+ dataDeFabricacao + ", quantidade = " + quantidade + ", valorUnitario = " + valorUnitario + "]";
	}
}
