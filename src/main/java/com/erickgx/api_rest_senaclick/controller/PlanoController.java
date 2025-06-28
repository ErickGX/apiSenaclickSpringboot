package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import com.erickgx.api_rest_senaclick.services.PlanoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/plano")
@Slf4j
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<Object> criarPlano(@RequestBody @Valid Plano plano) {

        Plano planoSalvo = planoService.cadastrarPlano(plano);
        URI location = URI.create("/plano/" + planoSalvo.getId());
        return ResponseEntity.created(location).body(planoSalvo);
    }


    @GetMapping
    public ResponseEntity<List<Plano>> listarPlanos(){
        List<Plano> planos = planoService.listarTodosPlanos();

        return planos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPlanoPorId(@PathVariable("id") Long id){
        Plano plano = planoService.buscarPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id){
        planoService.deletarPlanoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
