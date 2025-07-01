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

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private String artigo;

    @Column(nullable = false)
    private String noticia;

    @Column(nullable = false)
    private String edicao;

    @Column(nullable = false)
    private String texto_botao;

    @Column(nullable = false, unique = true)
    private String classe;


    @OneToMany(mappedBy = "plano")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Assinatura> assinaturas;


}
