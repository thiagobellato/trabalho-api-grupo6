package br.com.api.g6.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// public Pedido salvar(Pedido objetoPedido) {

	// for (Produto produto : objetoPedido.getProdutos()) {
	// Produto produtoBanco = produtoRepository.findByName(produto.getNome());

	// if (produtoBanco != null) {

	// produto.setId(produtoBanco.getId());
	// produto.setDescricao(produtoBanco.getDescricao());
	// produto.setDataDeFabricacao(produtoBanco.getDataDeFabricacao());
	// produto.setValorUnitario(produtoBanco.getValorUnitario());

	// } else {
	// // tratamento de erros -> futuro
	// }
	// }
	// return pedidoRepository.save(objetoPedido);
	// }

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

		// pedidoProduto.setValorTotal(valor);
		// emailService.envioEmailConfirmacaoPedido(null, pedidoDTO);
		// ResponseEntity.status(HttpStatus.CREATED).body("Pedido efetuado com
		// sucesso!");

		return salvarPedido.getId();
	}

	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	// public void apagar(Integer id) {
	// pedidoRepository.deleteById(id);
	// }

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

		// if (objetoPedido.getQuantidade() != null) {
		// registroAntigo.setQuantidade(objetoPedido.getQuantidade());
		// }

		if (objetoPedido.getData() != null) {
			registroAntigo.setData(objetoPedido.getData());
		}

		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}
}
