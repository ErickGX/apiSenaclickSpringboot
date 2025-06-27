package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.model.Assinatura;
import com.erickgx.api_rest_senaclick.repository.AssinaturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@Slf4j
public class ClientePlanoController {

    private final AssinaturaRepository repository;

    public ClientePlanoController(AssinaturaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    //diferentes codigos para lista vazia e lista com dados
    public ResponseEntity<List<Assinatura>> listarAdmins(){
        List<Assinatura> assinaturas = repository.findAll();

        if (assinaturas.isEmpty()){
            log.warn("Nenhuma assinatura encontrado."); // Log de aviso
            return ResponseEntity.notFound().build();
        }
        log.info("Listando {} assinaturas.", assinaturas.size()); // Log de sucesso
        return ResponseEntity.ok(assinaturas);
    }

}
