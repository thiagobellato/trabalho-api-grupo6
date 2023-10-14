package br.com.api.g6.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.api.g6.entities.Endereco;
import br.com.api.g6.services.EnderecoService;

public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping("/count")
	public Integer getCount() {
		return enderecoService.getCount();
	}

	@PostMapping("/salvar")
	public Endereco salvar(@RequestBody Endereco objetoEndereco) {
		return enderecoService.salvar(objetoEndereco);
	}

	@GetMapping("/listar")
	public List<Endereco> listar() {
		return enderecoService.listar();
	}

	@DeleteMapping("/delete/{id}")
	public void apagar(@PathVariable Integer id) {
		enderecoService.apagar(id);
	}

	@PutMapping("/atualizar/{id}")
	public Endereco atualizar(@PathVariable Integer id, @RequestBody Endereco objetoEndereco) {
		return enderecoService.atualizar(id, objetoEndereco);
	}
}
