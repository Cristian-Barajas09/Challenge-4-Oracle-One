package com.foroAlura.infra.errors.Exceptions;

public class EmailAlReadyExistsError extends RuntimeException {
    public EmailAlReadyExistsError(String elCorreoYaExiste) {
        super(elCorreoYaExiste);
    }
}
