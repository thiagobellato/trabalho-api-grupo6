package br.com.api.g6.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.g6.entities.Pedido;
import br.com.api.g6.entities.Produto;
import br.com.api.g6.repositories.PedidoRepository;
import br.com.api.g6.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	public Integer getCount() {
		return pedidoRepository.contar();
	}

	public Pedido salvar(Pedido objetoPedido) {

		for (Produto produto : objetoPedido.getProdutos()) {
			Produto produtoBanco = produtoRepository.findByName(produto.getNome());

			if (produtoBanco != null) {

				produto.setId(produtoBanco.getId());
				produto.setDescricao(produtoBanco.getDescricao());
				produto.setDataDeFabricacao(produtoBanco.getDataDeFabricacao());
				produto.setValorUnitario(produtoBanco.getValorUnitario());

			} else {
				// tratamento de erros -> futuro
			}
		}

		return pedidoRepository.save(objetoPedido);
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

		if (objetoPedido.getQuantidade() != null) {
			registroAntigo.setQuantidade(objetoPedido.getQuantidade());
		}

		if (objetoPedido.getData() != null) {
			registroAntigo.setData(objetoPedido.getData());
		}

		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}
}
