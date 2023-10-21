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
	public Categoria salvar(CategoriaDTO objetoCategoria) {
		Categoria categoriaNovo = new Categoria();
		categoriaNovo.setNome(objetoCategoria.getNome());
		categoriaNovo.setDescricao(objetoCategoria.getDescricao());
		return categoriaRepository.save(categoriaNovo);
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

<<<<<<< HEAD
=======
	// Put Teste
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git
	public CategoriaDTO atualizar(Integer id, CategoriaDTO objetoCategoria) {
<<<<<<< HEAD
		Categoria registroAntigo = acharId2(id);
=======
		CategoriaDTO registroAntigo = acharId(id);
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

<<<<<<< HEAD
		if (registroAntigo != null) {
			if (objetoCategoria.getNome() != null) {
				registroAntigo.setNome(objetoCategoria.getNome());
			}
=======
		if (objetoCategoria.getNome() != null) {
			registroAntigo.setNome(objetoCategoria.getNome());
		}
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

<<<<<<< HEAD
			if (objetoCategoria.getDescricao() != null) {
				registroAntigo.setDescricao(objetoCategoria.getDescricao());
			}
=======
		if (objetoCategoria.getDescricao() != null) {
			registroAntigo.setDescricao(objetoCategoria.getDescricao());
		}
		// Use o método save para atualizar o registro no repositório
		converterCategoriaDTO(null);
		CategoriaDTO categoriaAtualizada = converterCategoriaDTO(registroAntigo); // Converter para Categoria
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

<<<<<<< HEAD
			categoriaRepository.save(registroAntigo);
=======
		categoriaRepository.save(categoriaAtualizada);
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

<<<<<<< HEAD
			return converterCategoriaDTO(registroAntigo);
=======
		return converterCategoriaDTO(categoriaAtualizada); // Converter para CategoriaDTO e retornar
	}
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

<<<<<<< HEAD
		}
		return objetoCategoria;
	}
=======
	// Put OK
	// public CategoriaDTO atualizar(Integer id, CategoriaDTO objetoCategoria) {
	// Categoria registroAntigo = acharId2(id);
	//
	// if (registroAntigo != null) {
	// if (objetoCategoria.getNome() != null) {
	// registroAntigo.setNome(objetoCategoria.getNome());
	// }
	//
	// if (objetoCategoria.getDescricao() != null) {
	// registroAntigo.setDescricao(objetoCategoria.getDescricao());
	// }
	//
	// categoriaRepository.save(registroAntigo);
	//
	// return converterCategoriaDTO(registroAntigo);
	//
	// }
	// return objetoCategoria;
	// }
>>>>>>> branch 'main' of https://github.com/thiagobellato/trabalho-api-grupo6.git

	// Conversor
	public CategoriaDTO converterCategoriaDTO(CategoriaDTO registroAntigo) {
		CategoriaDTO categoriaConvertida = new CategoriaDTO();
		categoriaConvertida.setNome(registroAntigo.getNome());
		categoriaConvertida.setDescricao(registroAntigo.getDescricao());

		return categoriaConvertida;
	}
}
