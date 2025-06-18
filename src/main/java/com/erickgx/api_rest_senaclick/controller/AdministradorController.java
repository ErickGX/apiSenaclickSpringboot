package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.model.Administrador;
import com.erickgx.api_rest_senaclick.repository.AdministradorRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@Slf4j
 //habilita o controlle de logs para monitoramento
public class AdministradorController {


    //Injeção via Construtor (private final) é a mais recomendada em projetos modernos do Spring Boot,(Li na Doc oficial)
    private final AdministradorRepository repository;

    public AdministradorController(AdministradorRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity<Object> createAdmin(@RequestBody @Valid Administrador administrador ){
            try {
                repository.save(administrador);
                log.info("Administrador criado com sucesso: {}", administrador.getId());
                return ResponseEntity.created(URI.create("/admin/"+administrador.getId()))
                        .body(Map.of("mensagem", "Administrador cadastrado com sucesso"));

            }catch (Exception e){
                log.error("Erro ao cadastrar o admin: {}", e.getMessage(), e); //log de erro severo para depuração
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listarAdmins(){
        List<Administrador> admins = repository.findAll();

        if (admins.isEmpty()){
            log.warn("Nenhum admin encontrado."); // Log de aviso
            return ResponseEntity.notFound().build();
        }
        log.info("Listando {} produtos.", admins.size()); // Log de sucesso
        return ResponseEntity.ok(admins);
    }

}
