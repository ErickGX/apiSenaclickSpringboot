package com.erickgx.api_rest_senaclick.dtos.cliente.requests;

import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.model.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class dtoClientePlanoRequest {

        private Long id;

        private String nome;
}
