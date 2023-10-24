package br.com.api.g6.dto;

import java.time.LocalDate;

public class ProdutoDTO {

	private String nome;
	private String descricao;
	private LocalDate dataDeFabricacao;
	private Integer quantidade;
	private Double valorUnitario;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String nome, String descricao, LocalDate dataDeFabricacao, Integer quantidade,
			Double valorUnitario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataDeFabricacao = dataDeFabricacao;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
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

	public LocalDate getDataDeFabricacao() {
		return dataDeFabricacao;
	}

	public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
		this.dataDeFabricacao = dataDeFabricacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}