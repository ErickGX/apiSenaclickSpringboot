package com.erickgx.api_rest_senaclick.dtos.plano.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanoResponseDTO {

    private Long id;

    private String titulo;

    private Double preco;

    private String artigo;

    private String noticia;

    private String edicao;

    private String texto_botao;

    private String classe;
}
