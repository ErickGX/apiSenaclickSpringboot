package com.erickgx.api_rest_senaclick.dtos.plano.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlanoRequestDTO {

    @NotBlank(message = "O Campo titulo é obrigatório")
    private String titulo;

    @NotNull(message = "Necessário inserir o valor mesmo que seja 0")
    @DecimalMin(value = "0.0", message = "Não são permitidos valores negativos")
    private Double preco;

    @NotBlank(message = "O Campo Artigo é obrigatório")
    private String artigo;

    @NotBlank(message = "O Campo Noticia é obrigatório")
    private String noticia;

    @NotBlank(message = "O Campo Edição é obrigatório")
    private String edicao;

    @NotBlank(message = "O Campo texto para o botão é obrigatório")
    @JsonProperty("textoBotao")
    private String texto_botao;

    @NotBlank(message = "O Campo classe é obrigatório")
    private String classe;

}
