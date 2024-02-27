package br.com.fiaplanchesclient.infra.exception.handler;

public class PaymentRefusedException extends RuntimeException{

        public PaymentRefusedException(String message) {
            super(message);
        }
}
