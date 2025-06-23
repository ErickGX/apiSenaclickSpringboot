package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.model.ClientePlano;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.ClientePlanoRepository;
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

    private final ClientePlanoRepository repository;

    public ClientePlanoController(ClientePlanoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    //diferentes codigos para lista vazia e lista com dados
    public ResponseEntity<List<ClientePlano>> listarAdmins(){
        List<ClientePlano> admins = repository.findAll();

        if (admins.isEmpty()){
            log.warn("Nenhum admin encontrado."); // Log de aviso
            return ResponseEntity.notFound().build();
        }
        log.info("Listando {} produtos.", admins.size()); // Log de sucesso
        return ResponseEntity.ok(admins);
    }

}
