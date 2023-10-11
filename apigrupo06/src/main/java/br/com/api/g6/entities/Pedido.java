package br.com.api.g6.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String produto;
	private Integer quantidade;
	private Date data;
	//private String comprador;
	//private String vendedor;
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pedido(Integer id, String produto, Integer quantidade, Date data) {
		super();
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", data=" + data + "]";
	}

}
