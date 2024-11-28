package com.redesocial.exception;

/**
 * Exceção personalizada para erros relacionados a operações com posts na rede social.
 * <p>
 * Esta classe estende {@link RuntimeException} e é utilizada para representar problemas
 * que possam ocorrer durante a criação, manipulação ou outras operações envolvendo posts.
 * </p>
 */
public class PostException extends RuntimeException {
    /**
     * Construtor que cria uma exceção de post com uma mensagem específica.
     *
     * @param message Mensagem descritiva do erro relacionado ao post.
     */
    public PostException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma exceção de post com uma mensagem específica e a causa subjacente.
     *
     * @param message Mensagem descritiva do erro relacionado ao post.
     * @param cause   A causa subjacente do erro (outra exceção que originou este erro).
     */

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }
}