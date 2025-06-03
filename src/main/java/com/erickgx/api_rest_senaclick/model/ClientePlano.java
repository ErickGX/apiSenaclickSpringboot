package com.erickgx.api_rest_senaclick.model;

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

    private String pagamento;

    @Column(name = "data_assinatura")
    private LocalDateTime dataAssinatura;

}
