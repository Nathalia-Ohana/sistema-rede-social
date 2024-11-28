package com.redesocial.exception;

/**
 * Exceção personalizada para erros de validação na rede social.
 * <p>
 * Esta classe estende {@link RuntimeException} e é utilizada para representar falhas em
 * validações de dados, como entrada inválida ou violação de regras de negócio.
 * </p>
 */

public class ValidacaoException extends RuntimeException {
    /**
     * Construtor que cria uma exceção de validação com uma mensagem específica.
     *
     * @param message Mensagem descritiva do erro de validação.
     */

    public ValidacaoException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma exceção de validação com uma mensagem específica e a causa subjacente.
     *
     * @param message Mensagem descritiva do erro de validação.
     * @param cause   A causa subjacente do erro (outra exceção que originou este erro).
     */

    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
