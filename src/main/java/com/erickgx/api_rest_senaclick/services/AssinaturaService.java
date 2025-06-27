package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.model.Assinatura;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AssinaturaService {

    private final AssinaturaRepository repository;

    public AssinaturaService(AssinaturaRepository repository) {
        this.repository = repository;
    }

    public Assinatura salvarAssinatura(Plano plano, Cliente cliente, TipoPagamento tipoPagamento){

        Assinatura novaAssinatura = new Assinatura();
        novaAssinatura.setDataAssinatura(LocalDateTime.now());
        novaAssinatura.setPlano(plano);
        novaAssinatura.setCliente(cliente);
        novaAssinatura.setTipoPagamento(tipoPagamento);

      return repository.save(novaAssinatura);

    }
}
