package com.erickgx.api_rest_senaclick.controller;

import com.erickgx.api_rest_senaclick.dtos.dtoClientePlanoRequest;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.model.Assinatura;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.orchestrator.ClienteAssinaturaOrchestrator;
import com.erickgx.api_rest_senaclick.services.AssinaturaService;
import com.erickgx.api_rest_senaclick.services.ClienteService;
import com.erickgx.api_rest_senaclick.services.PlanoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {


//    //injeção de Dependencia Via construtor
//   private final ClienteRepository repository;
//
//    public ClienteController(ClienteRepository repository) {
//        this.repository = repository;
//    }

    private final ClienteAssinaturaOrchestrator orchestrator;
    private final ClienteService clienteService;

    public ClienteController(ClienteAssinaturaOrchestrator orchestrator, ClienteService clienteService) {
        this.orchestrator = orchestrator;
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Object> criarCliente(
            @RequestBody @Valid dtoClientePlanoRequest request) {

        Cliente clienteSalvo = orchestrator.cadastrarClienteComPlano(request.getIdPlano(), request.getCliente(), request.getTipoPagamento());

        URI location = URI.create("/cliente/" + clienteSalvo.getId());
        return ResponseEntity.created(location).body(clienteSalvo);
    }





}