package com.erickgx.api_rest_senaclick.exceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String mensagem){
        super(mensagem);
    }
}
