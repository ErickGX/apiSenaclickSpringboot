package com.erickgx.api_rest_senaclick.dtos.cliente.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ClienteUpdateDTO {


    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("primeiroNome") //o campo via JSON vem como primeiroNome , não é necessario alterar o frontend
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

}
