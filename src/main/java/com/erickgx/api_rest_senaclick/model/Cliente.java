package com.erickgx.api_rest_senaclick.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Cliente extends Usuario {

    //regra de negocio : um cliente só pode ter 1 assinatura relacionada com seu cadastro, porque não é permitido o mesmo email
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Assinatura assinatura;



}
