package br.com.fiaplanchesclient.infra.exception.handler;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
