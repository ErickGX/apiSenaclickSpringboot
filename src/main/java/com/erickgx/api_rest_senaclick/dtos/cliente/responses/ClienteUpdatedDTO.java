package com.erickgx.api_rest_senaclick.dtos.cliente.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteUpdatedDTO<ClienteUpdatedDTO> {
    private Long id;
    private String nome;
    private String sobrenome;

}
