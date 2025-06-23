package com.erickgx.api_rest_senaclick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private Double preco;
    @NotBlank
    private String artigo;
    @NotBlank
    private String noticia;
    @NotBlank
    private String edicao;
    @NotBlank
    private String textoBotao;
    @NotBlank
    private String classe;


    @OneToMany(mappedBy = "plano")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ClientePlano> clientes;


}
