package com.erickgx.api_rest_senaclick.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem){
            super(mensagem);
    }

}
