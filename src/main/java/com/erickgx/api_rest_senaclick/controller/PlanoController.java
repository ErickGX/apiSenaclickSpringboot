package com.erickgx.api_rest_senaclick.controller;


import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/plano")
@Slf4j
public class PlanoController {

    private final PlanoRepository repository;

    public PlanoController(PlanoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody @Valid Plano plano) {
        try {
            repository.save(plano);
            return ResponseEntity.created(URI.create("/cliente"+plano.getId())).body(plano);
        }catch (Exception e){
            log.error("Erro ao cadastrar o admin: {}", e.getMessage(), e); //log de erro severo para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping
    //diferentes codigos para lista vazia e lista com dados
    public ResponseEntity<List<Plano>> listarAdmins(){
        List<Plano> admins = repository.findAll();

        if (admins.isEmpty()){
            log.warn("Nenhum admin encontrado."); // Log de aviso
            return ResponseEntity.notFound().build();
        }
        log.info("Listando {} produtos.", admins.size()); // Log de sucesso
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable("id") Long id){
        return repository.findById(id)
                //.map() é útil quando queremos transformar um valor, caso ele exista(Optionals), muito util em Get id's.
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {  // Verifica se o ID existe antes de tentar deletar
            repository.deleteById(id);
            log.info("Admin cliente com id: {}", id); //log de sucesso
            return ResponseEntity.noContent().build(); // Retorna 204 No Content quando a exclusão é bem-sucedida
        } else {
            log.warn("Tentativa de cliente admin com ID inexistente: {}", id);  // Log de aviso
            return ResponseEntity.notFound().build(); // Retorna 404 caso o registro não exista
        }
    }
}
