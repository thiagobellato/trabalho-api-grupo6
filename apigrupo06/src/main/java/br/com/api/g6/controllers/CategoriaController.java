package br.com.api.g6.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.g6.entities.Categoria;
import br.com.api.g6.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping("/count")
	public Integer getCount() {
		return categoriaService.getCount();
	}

	@PostMapping("/salvar")
	public Categoria salvar(@RequestBody Categoria objetoCategoria) {
		return categoriaService.salvar(objetoCategoria);
	}

	@GetMapping("/{id}")
	public Categoria acharId(@PathVariable Integer id) {
		return categoriaService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Categoria> listar() {
		return categoriaService.listar();
	}

	@DeleteMapping("/delete/{id}")
	public void apagar(@PathVariable Integer id) {
		categoriaService.apagar(id);
	}

	// @DeleteMapping("/desativar/{id}")
	// public void apagarLogico(@PathVariable Integer id) {
	// categoriaService.apagarLogico(id);
	// }

	@PutMapping("/atualizar/{id}")
	public Categoria atualizar(@PathVariable Integer id, @RequestBody Categoria objetoCategoria) {
		return categoriaService.atualizar(id, objetoCategoria);
	}
}
