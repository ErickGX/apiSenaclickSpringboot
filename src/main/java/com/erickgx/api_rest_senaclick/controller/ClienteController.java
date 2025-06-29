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



    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = clienteService.listarTodosClientes();

        return clientes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable("id") Long id){
        Cliente cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.ok(cliente);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id){
        clienteService.deletarClientePorId(id);
        return ResponseEntity.noContent().build();
    }


    //Add commentMore actions
//
//    @GetMapping
//    //diferentes codigos para lista vazia e lista com dados
//    public ResponseEntity<List<Cliente>> listarAdmins(){
//        List<Cliente> admins = repository.findAll();
//
//        if (admins.isEmpty()){
//            log.warn("Nenhum admin encontrado."); // Log de aviso
//            return ResponseEntity.notFound().build();
//        }
//        log.info("Listando {} produtos.", admins.size()); // Log de sucesso
//        return ResponseEntity.ok(admins);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id){
//        return repository.findById(id)
//                //.map() é útil quando queremos transformar um valor, caso ele exista(Optionals), muito util em Get id's.
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//
//
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Cliente> atualizarClienteParcialmente(@RequestBody @Valid Cliente clienteAtualizacao, @PathVariable Long id){
//        return repository.findById(id)
//                .map(clienteExistente -> atualizarCamposPermitidos(clienteExistente, clienteAtualizacao))
//                .map(atualizado -> {
//                    log.info("Cliente atualizado com sucesso: {}", id);
//                    return ResponseEntity.ok(atualizado);
//                })
//                .orElseGet(() -> {
//                    log.warn("Tentativa de atualizar cliente inexistente: {}", id);
//                    return ResponseEntity.notFound().build();
//                });
//    }
//
//    private Cliente atualizarCamposPermitidos(Cliente destino , Cliente origem){
//        if (origem.getNome() != null) {
//            destino.setNome(origem.getNome());
//        }
//        if (origem.getSobrenome() != null) {
//            destino.setSobrenome(origem.getSobrenome());
//        }
//        // Ignora email e senha propositalmente
//        return repository.save(destino);
//    }
//
//
//
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable Long id) {
//        if (repository.existsById(id)) {  // Verifica se o ID existe antes de tentar deletar
//            repository.deleteById(id);
//            log.info("Admin cliente com id: {}", id); //log de sucesso
//            return ResponseEntity.noContent().build(); // Retorna 204 No Content quando a exclusão é bem-sucedida
//        } else {
//            log.warn("Tentativa de cliente admin com ID inexistente: {}", id);  // Log de aviso
//            return ResponseEntity.notFound().build(); // Retorna 404 caso o registro não exista
//        }
//    }





}