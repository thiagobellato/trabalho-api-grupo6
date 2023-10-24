package br.com.api.g6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g6.entities.Pedido;
import br.com.api.g6.entities.Usuario;
import br.com.api.g6.services.EmailService;
import br.com.api.g6.services.PedidoService;
import br.com.api.g6.services.UsuarioService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	@PostMapping("/salvar")
	public void salvar(@RequestParam String email, @RequestBody Pedido objetoPedido) {
		pedidoService.salvar(objetoPedido);
		Usuario usuario = usuarioService.findByEmail(email);
		emailService.envioEmailPedidoFinalizado(usuario,objetoPedido.getId());
	}

	@GetMapping("/{id}")
	public Pedido acharId(@PathVariable Integer id) {
		return pedidoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Pedido> listar() {
		return pedidoService.listar();
	}

	@DeleteMapping("/desativar/{id}")
	public void apagarLogico(@PathVariable Integer id) {
		pedidoService.apagarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Pedido atualizar(@PathVariable Integer id, @RequestBody Pedido objetoPedido) {
		return pedidoService.atualizar(id, objetoPedido);
	}
}