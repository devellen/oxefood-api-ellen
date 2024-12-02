package br.com.ifpe.oxefood.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntregadorException extends RuntimeException {
    public static final String MSG_VALOR_MINIMO_FRETE = "Não é permitido inserir produtos com valores inferiores a R$ 10.";

    public EntregadorException(String msg) {

        super(String.format(msg));
    }
}