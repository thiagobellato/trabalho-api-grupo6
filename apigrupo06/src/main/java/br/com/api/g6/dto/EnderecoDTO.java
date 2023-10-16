package br.com.api.g6.dto;

public class EnderecoDTO {
	private String cep;
	private String complemento2;
	private String numero;
	private String pais;

	public EnderecoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnderecoDTO(String cep, String complemento2, String numero, String pais) {
		super();
		this.cep = cep;
		this.complemento2 = complemento2;
		this.numero = numero;
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
