package com.erickgx.api_rest_senaclick.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

//public class ApiError {
//
//    //Formatação padronizado para erros da API
//    private LocalDateTime timestamp;
//    private int status;
//    private String error;
//    private String message;
//    private String path;
//
//    public ApiError(LocalDateTime timestamp, int status, String error, String message, String path) {
//        this.timestamp = timestamp;
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }

//versão aprimorada com Record , para aplicação do principio DRY - Dont repeat Yourself
public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public static ApiError of(HttpStatus status, String message, String path) {
        return new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path);
    }
}
