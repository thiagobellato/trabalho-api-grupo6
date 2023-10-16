package br.com.api.g6.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.g6.entities.Pedido;
import br.com.api.g6.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Integer getCount() {
		return pedidoRepository.contar();
	}

	public Pedido salvar(Pedido objetoPedido) {
		return pedidoRepository.save(objetoPedido);
	}

	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public void apagar(Integer id) {
		pedidoRepository.deleteById(id);
	}

	// public void apagarLogico(Integer id) {
	// Pedido objetoPedido = acharId(id);
	// if(objetoPedido!=null) {
	// objetoPedido.setAtivo(false);
	// pedidoRepository.save(objetoPedido);
	// }
	// }

	// public Pedido atualizar(Integer id, Pedido objetoPedido) {
	// Pedido registroAntigo = acharId(id);
	// if (objetoPedido.getProduto() != null) {
	// registroAntigo.setProduto(objetoPedido.getProduto());
	// }
	// if (objetoPedido.getQuantidade() != null) {
	// registroAntigo.setQuantidade(objetoPedido.getQuantidade());
	// }
	// if (objetoPedido.getDate() != null) {
	// registroAntigo.setDate(objetoPedido.getDate());
	// }
	// registroAntigo.setId(id);
	// return pedidoRepository.save(registroAntigo);
	// }
}
