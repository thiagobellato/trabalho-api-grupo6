package br.com.api.g6.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.api.g6.entities.Endereco;
import br.com.api.g6.repositories.EnderecoRepository;

public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Integer getCount() {
		return enderecoRepository.contar();
	}

	public Endereco salvar(Endereco objetoEndereco) {
		return enderecoRepository.save(objetoEndereco);
	}

	public Endereco acharId(Integer id) {
		return enderecoRepository.findById(id).get();
	}

	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	public void apagar(Integer id) {
		enderecoRepository.deleteById(id);
	}

	public Endereco atualizar(Integer id, Endereco objetoEndereco) {
		Endereco registroAntigo = acharId(id);
		if (objetoEndereco.getCep() != null) {
			registroAntigo.setCep(objetoEndereco.getCep());
		}
		if (objetoEndereco.getComplemento2() != null) {
			registroAntigo.setComplemento2(objetoEndereco.getComplemento2());
		}
		if (objetoEndereco.getNumero() != null) {
			registroAntigo.setNumero(objetoEndereco.getNumero());
		}
		if (objetoEndereco.getPais() != null) {
			registroAntigo.setPais(objetoEndereco.getPais());
		}
		return enderecoRepository.save(registroAntigo);
	}
}
