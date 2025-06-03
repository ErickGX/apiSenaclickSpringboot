package com.erickgx.api_rest_senaclick.controller;

import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody @Valid Cliente cliente) {
      try {
           repository.save(cliente);
           return ResponseEntity.created(URI.create("/cliente"+cliente.getId())).body(cliente);
      }catch (Exception e){
          e.printStackTrace();
          String erro = e.getMessage();
          return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId (@PathVariable("id") Long id){
        Optional<Cliente> tipo = repository.findById(id);
        if (tipo.isPresent()){
            return ResponseEntity.ok(tipo.get());
        }else {
            return ResponseEntity.notFound().build(); //build é para montar o objeto not found
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarTipo(@RequestBody Cliente tipo, @PathVariable Long id){
        try {
            //salvar no banco
            repository.save(tipo);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")//PathVar especifica que vai ser recebida via parametro da requisicao
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}