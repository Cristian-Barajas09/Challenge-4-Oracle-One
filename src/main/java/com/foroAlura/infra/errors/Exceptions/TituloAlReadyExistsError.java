package com.foroAlura.infra.errors.Exceptions;

public class TituloAlReadyExistsError extends RuntimeException{
    public TituloAlReadyExistsError(String m){
        super(m);
    }
}
