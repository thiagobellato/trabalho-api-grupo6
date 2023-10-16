package br.com.api.g6.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id;

	@Column(name = "cep_endereco")
	private String cep;

	@Column(name = "rua_endereco")
	private String logradouro;

	@Column(name = "numero_endereco")
	private String numero;

	@Column(name = "complemento_endereco")
	private String complemento;

	@Column(name = "complemento2_endereco")
	private String complemento2;

	@Column(name = "bairro_endereco")
	private String bairro;

	@Column(name = "cidade_endereco")
	private String localidade;

	@Column(name = "uf_endereco")
	private String uf;

	@Column(name = "pais_endereco")
	private String pais;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, String cep, String logradouro, String numero, String complemento, String complemento2,
			String bairro, String localidade, String uf, String pais) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Endereco [id = " + id + ", cep = " + cep + ", logradouro = " + logradouro + ", numero = " + numero
				+ ", complemento = " + complemento + ", complemento2 = " + complemento2 + ", bairro = " + bairro
				+ ", localidade = " + localidade + ", uf = " + uf + ", pais = " + pais + "]";
	}
}
