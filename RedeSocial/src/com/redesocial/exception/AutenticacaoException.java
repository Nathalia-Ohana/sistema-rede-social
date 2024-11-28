package com.redesocial.exception;

/**
 * Exceção personalizada para erros relacionados à autenticação na rede social.
 * <p>
 * Esta classe estende {@link RuntimeException} e é usada para sinalizar problemas
 * que ocorrem durante os processos de autenticação.
 * </p>
 */
public class AutenticacaoException extends RuntimeException {
    /**
     * Construtor que cria uma exceção de autenticação com uma mensagem específica.
     *
     * @param message Mensagem descritiva do erro de autenticação.
     */
    public AutenticacaoException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma exceção de autenticação com uma mensagem específica e a causa subjacente.
     *
     * @param message Mensagem descritiva do erro de autenticação.
     * @param cause   A causa subjacente do erro (outra exceção).
     */

    public AutenticacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
