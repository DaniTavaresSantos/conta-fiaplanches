package br.com.fiaplanchesclient.infra.exception.handler;

import br.com.fiaplanchesclient.generated.Generated;

@Generated
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
