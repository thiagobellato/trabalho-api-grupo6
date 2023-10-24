package br.com.api.g6.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g6.dto.PedidoDTO;
import br.com.api.g6.entities.Pedido;
import br.com.api.g6.entities.PedidoProduto;
import br.com.api.g6.entities.Produto;
import br.com.api.g6.repositories.PedidoRepository;
import br.com.api.g6.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public Integer salvar(PedidoDTO pedidoDTO) {
		Pedido salvarPedido = new Pedido();
		PedidoProduto pedidoProduto = new PedidoProduto();
		// salvarPedido.setItemQuantidade(pedidoDTO.getItemQuantidade());
		salvarPedido.setId(pedidoDTO.getId_usuario());

		Double valor = 0.0;
		List<Produto> produtos = new ArrayList<>();
		for (Integer idProduto : pedidoDTO.getId_produto()) {
			Produto produto = produtoRepository.findById(idProduto).get();
			produtos.add(produto);
			valor += pedidoDTO.getQnt_produto() * produto.getValorUnitario();

			pedidoProduto.setQnt_item(pedidoDTO.getQnt_produto());
		}

		salvarPedido.setProdutos(produtos);
		salvarPedido.setData(pedidoDTO.getData());
		pedidoRepository.save(salvarPedido);

		return salvarPedido.getId();
	}

	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public void apagarLogico(Integer id) {

		Pedido objetoPedido = acharId(id);

		if (objetoPedido != null) {
			objetoPedido.setAtivo(false);
			pedidoRepository.save(objetoPedido);
		}
	}

	public Pedido atualizar(Integer id, Pedido objetoPedido) {
		Pedido registroAntigo = acharId(id);

		if (objetoPedido.getProdutos() != null) {
			registroAntigo.setProdutos(objetoPedido.getProdutos());
		}

		if (objetoPedido.getData() != null) {
			registroAntigo.setData(objetoPedido.getData());
		}

		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}
}
