package com.foroAlura.infra.errors.Exceptions;

public class TokenValidationError extends RuntimeException{
    public TokenValidationError(String m) {
        super(m);
    }
}
