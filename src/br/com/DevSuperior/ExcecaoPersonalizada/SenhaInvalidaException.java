package br.com.DevSuperior.ExcecaoPersonalizada;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException() {
        super("Erro: Senha inválida!");
    }
}
