package br.com.api.g6.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.g6.entities.Pedido;
import br.com.api.g6.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping("/count")
	public Integer getCount() {
		return pedidoService.getCount();
	}

	@PostMapping("/salvar")
	public Pedido salvar(@RequestBody Pedido objetoPedido) {
		return pedidoService.salvar(objetoPedido);
	}

	@GetMapping("/{id}")
	public Pedido acharId(@PathVariable Integer id) {
		return pedidoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Pedido> listar() {
		return pedidoService.listar();
	}

	@DeleteMapping("/delete/{id}")
	public void apagar(@PathVariable Integer id) {
		pedidoService.apagar(id);
	}

	// @DeleteMapping("/desativar/{id}")
	// public void apagarLogico(@PathVariable Integer id) {
	// pedidoService.apagarLogico(id);
	// }

	// @PutMapping("/atualizar/{id}")
	// public Pedido atualizar(@PathVariable Integer id, @RequestBody Pedido
	// objetoPedido) {
	// return pedidoService.atualizar(id, objetoPedido);
	// }
}