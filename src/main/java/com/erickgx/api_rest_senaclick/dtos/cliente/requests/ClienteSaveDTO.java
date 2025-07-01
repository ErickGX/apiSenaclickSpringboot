package com.erickgx.api_rest_senaclick.dtos.cliente.requests;

import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ClienteSaveDTO {


    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("primeiroNome") //o campo via JSON vem como primeiroNome , não é necessario alterar o frontend
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres e no maximo 50")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotNull
    private Long idPlano;

    @NotNull
    private TipoPagamento pagamento;

}
