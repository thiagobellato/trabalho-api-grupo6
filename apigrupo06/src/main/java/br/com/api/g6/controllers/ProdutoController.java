package br.com.api.g6.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g6.dto.MessageResponseDTO;
import br.com.api.g6.entities.Produto;
import br.com.api.g6.services.EmailService;
import br.com.api.g6.services.ProdutoService;
import br.com.api.g6.services.UsuarioService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	UsuarioService usuarioService;

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping("/count")
	public Integer getCount() {
		return produtoService.getCount();
	}

	// @PostMapping("/salvar")
	// public Produto salvar(@RequestBody Produto objetoProduto) {
	// return produtoService.salvar(objetoProduto);
	// }

	@PostMapping("/salvar")
	public ResponseEntity<MessageResponseDTO> salvar(@RequestBody Produto objetoProduto) {
		produtoService.salvar(objetoProduto);
		// mensagem para retorno positivo
		return ResponseEntity.ok(new MessageResponseDTO("Produto salvo com sucesso!"));
	}

	@GetMapping("/{id}")
	public Produto acharId(@PathVariable Integer id) {
		return produtoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Produto> listar() {
<<<<<<< HEAD
		emailService.envioEmailPromocional();
=======
		// emailService.envioEmailPromocional();
>>>>>>> 99cd0331d724aa6151077ef6c20b34b0e2be7626
		return produtoService.listar();
	}

	@DeleteMapping("/delete/{id}")
	public void apagar(@PathVariable Integer id) {
		produtoService.apagar(id);
	}

	@PutMapping("/atualizar/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto objetoProduto) {
		return produtoService.atualizar(id, objetoProduto);
	}
}
