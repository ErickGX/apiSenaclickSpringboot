package com.erickgx.api_rest_senaclick.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String mensagem){
        super(mensagem);
    }

}
