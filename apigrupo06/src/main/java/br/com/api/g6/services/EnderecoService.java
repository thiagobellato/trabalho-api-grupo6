package br.com.api.g6.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.g6.entities.Endereco;
import br.com.api.g6.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Integer getCount() {
		return enderecoRepository.contar();
	}

	public Endereco salvar(Endereco objetoEndereco) {
		Endereco viaCep = pesquisarEndereco(objetoEndereco.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(objetoEndereco.getCep());
		enderecoNovo.setComplemento(viaCep.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setComplemento2(objetoEndereco.getComplemento2());
		enderecoNovo.setPais(objetoEndereco.getPais());
		enderecoNovo.setNumero(objetoEndereco.getNumero());
		
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

	public Endereco pesquisarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<>();
		params.put("cep", cep);
		return restTemplate.getForObject(uri, Endereco.class, params);
	}

}
