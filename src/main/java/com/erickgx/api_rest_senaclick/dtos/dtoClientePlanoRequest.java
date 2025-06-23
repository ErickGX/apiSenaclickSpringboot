package com.erickgx.api_rest_senaclick.dtos;

import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.model.Cliente;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class dtoClientePlanoRequest {
    @Valid
    private Cliente cliente;
    private Long idPlano;
    private TipoPagamento tipoPagamento;
}
