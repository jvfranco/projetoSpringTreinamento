package br.com.cadastro.funcionario.exceptions;

public class FuncionarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FuncionarioNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
}
