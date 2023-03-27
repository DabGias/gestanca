package br.com.fiap.gestanca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NaoAutorizadoException extends RuntimeException {
    public NaoAutorizadoException(String msg) {
        super(msg);
    }
}
