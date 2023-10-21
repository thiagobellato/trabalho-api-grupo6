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

import br.com.api.g6.dto.CategoriaDTO;
import br.com.api.g6.entities.Categoria;
import br.com.api.g6.services.CategoriaService;
import br.com.api.g6.services.EmailService;
import javax.mail.MessagingException;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Autowired
	CategoriaService categoriaService;

	@GetMapping("/count")
	public Integer getCount() throws MessagingException {
		// emailService.envioEmail();
		// emailService.envioEmailTeste();
		return categoriaService.getCount();
	}

	@PostMapping("/salvar")
	public Categoria salvar(@RequestBody CategoriaDTO objetoCategoria) {
		return categoriaService.salvar(objetoCategoria);
	}

	@GetMapping("/{id}")
	public CategoriaDTO acharId(@PathVariable Integer id) {
		return categoriaService.acharId(id);
	}

	@GetMapping("/listar")
	public List<CategoriaDTO> listar() {
		emailService.envioEmail();
		return categoriaService.listar();
	}

	@DeleteMapping("/delete/{id}")
	public void apagar(@PathVariable Integer id) {
		categoriaService.apagar(id);
	}

	@PutMapping("/atualizar/{id}")
	public CategoriaDTO atualizar(@PathVariable Integer id, @RequestBody CategoriaDTO objetoCategoria) {
		return categoriaService.atualizar(id, objetoCategoria);
	}
}
