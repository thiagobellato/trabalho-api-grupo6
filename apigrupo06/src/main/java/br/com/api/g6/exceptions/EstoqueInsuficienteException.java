package br.com.api.g6.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {

	public EstoqueInsuficienteException(String mensagem) {
		super(mensagem);
	}
}
