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

	// Post (Salvar, cadastra uma nova categoria)
	public CategoriaDTO salvar(CategoriaDTO objetoCategoria) {
		Categoria categoriaNovo = new Categoria();
		converterCategoriaDTO(categoriaNovo);
		categoriaNovo.setNome(objetoCategoria.getNome());
		categoriaNovo.setDescricao(objetoCategoria.getDescricao());
		categoriaRepository.save(categoriaNovo);
		return objetoCategoria;

	}

	// Get (Busca uma categoria por ID)
	public CategoriaDTO acharId(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).get();
		CategoriaDTO dtoCategoria = converterCategoriaDTO(categoria);
		return dtoCategoria;
	}

	// Get (Busca uma categoria por ID)
	public Categoria acharId2(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	// Get (Lista todas as categorias)
	public List<CategoriaDTO> listar() {
		List<CategoriaDTO> dtoCategorias = new ArrayList<>();
		List<Categoria> infoCategorias = categoriaRepository.findAll();
		for (Categoria categoria : infoCategorias) {
			dtoCategorias.add(converterCategoriaDTO(categoria));
		}
		return dtoCategorias;
	}

	public void apagar(Integer id) {
		categoriaRepository.deleteById(id);
	}

	public CategoriaDTO atualizar(Integer id, CategoriaDTO objetoCategoria) {
		Categoria registroAntigo = acharId2(id);

		if (objetoCategoria.getNome() != null) {
			registroAntigo.setNome(objetoCategoria.getNome());
		}
		if (objetoCategoria.getDescricao() != null) {
			registroAntigo.setDescricao(objetoCategoria.getDescricao());
		}
		categoriaRepository.save(registroAntigo);
		// return converterCategoriaDTO(registroAntigo);

		return objetoCategoria;
	}

	// Conversor
	public CategoriaDTO converterCategoriaDTO(Categoria categoria) {
		CategoriaDTO categoriaConvertida = new CategoriaDTO();
		categoriaConvertida.setNome(categoria.getNome());
		categoriaConvertida.setDescricao(categoria.getDescricao());

		return categoriaConvertida;
	}
}