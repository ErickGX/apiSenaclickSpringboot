


package com.erickgx.api_rest_senaclick.orchestrator;


import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.mappers.ClienteMapper;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.AssinaturaRepository;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import com.erickgx.api_rest_senaclick.services.AssinaturaService;
import com.erickgx.api_rest_senaclick.services.ClienteService;
import com.erickgx.api_rest_senaclick.services.PlanoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//para garantir atomicidade da transaçaõ da minha regra de negocio foi necessario criar este ClienteAssinaturaOrchestrator
//como não posso usar @Transacional no controller, e tambem nao tenho como garantir que 3 metodos de 3 Services diferentes trabalhem em atomicidade
//no controller sem usar @Transacional nele , criei uma classe Orquestradora que tera a responsabilidade de Juntar esses 3 metodos e garantir a
//Atomicidade da minha regra de negocio seguindo o principio S.O.L.I.D
public class ClienteAssinaturaOrchestrator {

   private final ClienteService  clienteService;
   private final PlanoService planoService;
   private final AssinaturaService assinaturaService;
   private final ClienteMapper mapper;

    public ClienteAssinaturaOrchestrator(ClienteService clienteService, PlanoService planoService, AssinaturaService assinaturaService, ClienteMapper mapper) {
        this.clienteService = clienteService;
        this.planoService = planoService;
        this.assinaturaService = assinaturaService;
        this.mapper = mapper;
    }

    @Transactional
    public ClienteResponseDTO cadastrarClienteComPlano(Long idPlano, ClienteRequestDTO dto , TipoPagamento pagamento) {
        Cliente cliente = mapper.toEntity(dto);

        Plano planoRecuperado = planoService.obterPlanoPorId(idPlano);
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        assinaturaService.salvarAssinatura(planoRecuperado, novoCliente,pagamento);

        ClienteResponseDTO response = mapper.toResponse(novoCliente);

        return response;
    }

}
