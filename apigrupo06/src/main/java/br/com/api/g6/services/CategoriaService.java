package br.com.api.g6.services;

import java.util.ArrayList;
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

	public CategoriaDTO acharId(Integer id) {
		CategoriaDTO dtoCategoria = new CategoriaDTO();
		Categoria categoria = new Categoria();
		dtoCategoria = converterCategoriaDTO(categoria);
		return dtoCategoria;
	}

	public List<CategoriaDTO> listar() {
		List<CategoriaDTO> dtoCategorias = new ArrayList<>();
		List<Categoria> infoCategorias = categoriaRepository.findAll();
		for (Categoria categoria : infoCategorias) {
			dtoCategorias.add(converterCategoriaDTO(categoria));
		}
		return dtoCategorias;
	}

	public CategoriaDTO converterCategoriaDTO(Categoria categoria) {
		CategoriaDTO categoriaConvertida = new CategoriaDTO();
		categoriaConvertida.setNome(categoria.getNome());
		categoriaConvertida.setDescricao(categoria.getDescricao());

		return categoriaConvertida;
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

	//PUT
//	public Categoria atualizar(Integer id, Categoria objetoCategoria) {
//		Categoria registroAntigo = acharId(id);
//		if (objetoCategoria.getNome() != null) {
//			registroAntigo.setNome(objetoCategoria.getNome());
//		}
//
//		if (objetoCategoria.getDescricao() != null) {
//			registroAntigo.setDescricao(objetoCategoria.getDescricao());
//		}
//
//		registroAntigo.setId(id);
//		return categoriaRepository.save(registroAntigo);
//		// Precisa incluir a lista de produtos?
//	}
}
