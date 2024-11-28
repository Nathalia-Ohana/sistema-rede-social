package com.redesocial.exception;

/**
 * Exceção personalizada para erros relacionados a operações com usuários na rede social.
 * <p>
 * Esta classe estende {@link RuntimeException} e é utilizada para representar problemas
 * que possam ocorrer durante o cadastro, manipulação ou qualquer operação relacionada a usuários.
 * </p>
 */

public class UsuarioException extends RuntimeException {
    /**
     * Construtor que cria uma exceção de usuário com uma mensagem específica.
     *
     * @param message Mensagem descritiva do erro relacionado ao usuário.
     */

    public UsuarioException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma exceção de usuário com uma mensagem específica e a causa subjacente.
     *
     * @param message Mensagem descritiva do erro relacionado ao usuário.
     * @param cause   A causa subjacente do erro (outra exceção que originou este erro).
     */

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
}

