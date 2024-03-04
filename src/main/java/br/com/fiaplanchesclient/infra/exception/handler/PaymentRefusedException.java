package br.com.fiaplanchesclient.infra.exception.handler;

import br.com.fiaplanchesclient.generated.Generated;

@Generated
public class PaymentRefusedException extends RuntimeException{

        public PaymentRefusedException(String message) {
            super(message);
        }
}
