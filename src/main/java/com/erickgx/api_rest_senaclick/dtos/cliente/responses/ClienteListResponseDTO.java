package com.erickgx.api_rest_senaclick.dtos.cliente.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteListResponseDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
}
