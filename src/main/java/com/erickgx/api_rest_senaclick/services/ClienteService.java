package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.ClientePlano;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.ClientePlanoRepository;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    private final ClienteRepository CLrepository;
    private final PlanoRepository PLrepository;
    private final ClientePlanoRepository CLPLrepository;

    public ClienteService(ClienteRepository CLrepository, PlanoRepository PLrepository, ClientePlanoRepository CLPLrepository) {
        this.CLrepository = CLrepository;
        this.PLrepository = PLrepository;
        this.CLPLrepository = CLPLrepository;
    }

    public Cliente CadastrarClienteEAssinatura(Cliente cliente, Long idPlano, TipoPagamento pagamento){


       // Verifico se o plano existe antes de seguir com o cadastro do cliente
       Plano plano= PLrepository.findById(idPlano)
             .orElseThrow(() -> new ResourceNotFoundException("Plano com ID "+ idPlano + " não encontrado"));

       // Cadastro novo cliente
       Cliente novoCliente = CLrepository.save(cliente);

       // Criação da assinatura associando o cliente e o plano
        ClientePlano assinatura = new ClientePlano();
        assinatura.setCliente(novoCliente);
        assinatura.setPlano(plano);
        assinatura.setDataAssinatura(LocalDateTime.now());
        assinatura.setTipoPagamento(pagamento);

        // Registrando Assinatura
        CLPLrepository.save(assinatura);

        return novoCliente;
    }
}
