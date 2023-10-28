package br.com.DevSuperior.ExcecaoPersonalizada;

public class UsuarioInvalidoException extends RuntimeException{
    public UsuarioInvalidoException() {
        super("Erro: Usuário inválido!");
    }
}
