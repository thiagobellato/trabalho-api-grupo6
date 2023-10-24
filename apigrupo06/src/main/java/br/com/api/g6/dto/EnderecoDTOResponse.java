package br.com.api.g6.dto;

public class EnderecoDTOResponse {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String complemento2;
	private String bairro;
	private String localidade;
	private String uf;
	private String pais;

	public EnderecoDTOResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnderecoDTOResponse(String cep, String logradouro, String numero, String complemento, String complemento2,
			String bairro, String localidade, String uf, String pais) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.complemento2 = complemento2;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
