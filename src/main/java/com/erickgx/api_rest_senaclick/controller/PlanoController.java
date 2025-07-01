package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.plano.requests.PlanoRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.plano.responses.PlanoResponseDTO;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.services.PlanoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<PlanoResponseDTO> criarPlano(@RequestBody @Valid PlanoRequestDTO plano) {

        PlanoResponseDTO planoSalvo = planoService.cadastrarPlano(plano);
        URI location = URI.create("/plano/" + planoSalvo.getId());
        return ResponseEntity.created(location).body(planoSalvo);
    }


    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> listarPlanos(){
        List<PlanoResponseDTO> planos = planoService.listarTodosPlanos();

        return planos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> buscarPlanoPorId(@PathVariable("id") Long id){
        PlanoResponseDTO plano = planoService.buscarPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id){
        planoService.deletarPlanoPorId(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PlanoResponseDTO> atualizarPlano(@PathVariable("id") Long id, @RequestBody @Valid PlanoRequestDTO dto){
//
//        PlanoResponseDTO atualizado = planoService.atualizarPlano(id, dto);
//
//        return ResponseEntity.ok().body(atualizado);
//    }
}
