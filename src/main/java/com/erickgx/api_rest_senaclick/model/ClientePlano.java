package com.erickgx.api_rest_senaclick.model;

import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ClientePlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Plano plano;

    @Enumerated(EnumType.STRING) //Valores fixos não variaveis é recomendado o uso de Enums
    @JsonProperty("pagamento") //o campo via JSON vem como pagamento , não é necessario alterar o frontend
    private TipoPagamento tipoPagamento;

    @Column(name = "data_assinatura")
    private LocalDateTime dataAssinatura;

}
