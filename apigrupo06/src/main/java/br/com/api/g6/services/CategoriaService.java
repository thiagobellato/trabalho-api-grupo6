package br.com.api.g6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g6.dto.CategoriaDTO;
import br.com.api.g6.entities.Categoria;
import br.com.api.g6.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Integer getCount() {
		return categoriaRepository.contar();
	}

	public Categoria salvar(CategoriaDTO objetoCategoria) {
		Categoria categoriaNovo = new Categoria();
		categoriaNovo.setNome(objetoCategoria.getNome());
		categoriaNovo.setDescricao(objetoCategoria.getDescricao());
		return categoriaRepository.save(categoriaNovo);
	}

	public Categoria acharId(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	public List<Categoria> listar() {
//		Categoria categoriaNovo = new Categoria();	
//		categoriaNovo.getNome();
//		categoriaNovo.getDescricao();
		return categoriaRepository.findAll();
	}

	public void apagar(Integer id) {
		categoriaRepository.deleteById(id);
	}

	// public void apagarLogico(Integer id) {
	// Categoria objetoCategoria = acharId(id);
	// if(objetoCategoria!=null) {
	// objetoCategoria.setAtivo(false);
	// categoriaRepository.save(objetoCategoria);
	// }
	// }

	public Categoria atualizar(Integer id, Categoria objetoCategoria) {
		Categoria registroAntigo = acharId(id);
		if (objetoCategoria.getNome() != null) {
			registroAntigo.setNome(objetoCategoria.getNome());
		}

		if (objetoCategoria.getDescricao() != null) {
			registroAntigo.setDescricao(objetoCategoria.getDescricao());
		}

		registroAntigo.setId(id);
		return categoriaRepository.save(registroAntigo);
		// Precisa incluir a lista de produtos?
	}
}
