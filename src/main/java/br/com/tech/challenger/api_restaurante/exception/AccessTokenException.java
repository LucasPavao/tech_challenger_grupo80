package br.com.tech.challenger.api_restaurante.exception;

public class AccessTokenException extends RuntimeException {
    public AccessTokenException(String message) {
        super(message);
    }
}
