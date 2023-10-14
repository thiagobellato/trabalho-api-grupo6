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
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private Integer id;

	@Column(name = "pedido_produto")
	private String produto;
	@Column(name = "pedido_quantidade")
	private Integer quantidade;
	@Column(name = "pedido_date")
	private Date data;
	// private String comprador;
	// private String vendedor;
	@ManyToMany
	@JoinTable(name = "pedido_produto", 
	joinColumns = @JoinColumn(name = "pedido_id"), 
	inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos;

	
}
