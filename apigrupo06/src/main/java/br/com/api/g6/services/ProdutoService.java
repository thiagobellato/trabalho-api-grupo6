package br.com.api.g6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g6.dto.ProdutoDTO;
import br.com.api.g6.entities.Produto;
import br.com.api.g6.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public Integer getCount() {
		return produtoRepository.contar();
	}

	public Produto salvar(Produto objProduto) {
		ProdutoDTO produtoNovo = new ProdutoDTO();
		produtoNovo.setNome(objProduto.getNome());
		return produtoRepository.save(produtoNovo);
	}

	public Produto acharId(Integer id) {
		return produtoRepository.findById(id).get();
	}

	public List<Produto> listar(Integer idPedido) {
		return produtoRepository.listarProdutosPorPedido(idPedido);
	}

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public void apagar(Integer id) {
		produtoRepository.deleteById(id);
	}

	public Produto atualizar(Integer id, Produto objetoProduto) {
		Produto registroAntigo = acharId(id);

		if (objetoProduto.getDataDeFabricacao() != null) {
			registroAntigo.setDataDeFabricacao(objetoProduto.getDataDeFabricacao());
		}

		if (objetoProduto.getDescricao() != null) {
			registroAntigo.setDescricao(objetoProduto.getDescricao());
		}

		if (objetoProduto.getNome() != null) {
			registroAntigo.setNome(objetoProduto.getNome());
		}

		if (objetoProduto.getQuantidade() != null) {
			registroAntigo.setQuantidade(objetoProduto.getQuantidade());
		}

		if (objetoProduto.getValorUnitario() != null) {
			registroAntigo.setValorUnitario(objetoProduto.getValorUnitario());
		}

		return produtoRepository.save(registroAntigo);
	}
}
