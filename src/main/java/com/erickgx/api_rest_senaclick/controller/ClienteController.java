package com.erickgx.api_rest_senaclick.controller;

import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteSaveDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteUpdateDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteListResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteUpdatedDTO;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.orchestrator.ClienteAssinaturaOrchestrator;
import com.erickgx.api_rest_senaclick.services.ClienteService;
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

    //Uso de orchestrator para regra de negocio complexa envolvendo varias services
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(
            @RequestBody @Valid ClienteSaveDTO request) {

        ClienteResponseDTO clienteSalvo = orchestrator.cadastrarClienteComPlano(request.getIdPlano(), request, request.getPagamento());

        URI location = URI.create("/cliente/" + clienteSalvo.getId());
        return ResponseEntity.created(location).body(clienteSalvo);
    }



    @GetMapping
    public ResponseEntity<List<ClienteListResponseDTO>> listarClientes(){
        List<ClienteListResponseDTO> clientes = clienteService.listarTodosClientes();

        return clientes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteListResponseDTO> buscarClienteId(@PathVariable("id") Long id){
        ClienteListResponseDTO cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.ok(cliente);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id){
        clienteService.deletarClientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteUpdatedDTO> atualizarPorId(@PathVariable("id") Long id, @RequestBody @Valid ClienteUpdateDTO cliente){
     ClienteUpdatedDTO atualizado =  clienteService.atualizarClientePartial(id,cliente);

     //location URI é reservado apenas para criação, indicando a localizacao do novo recurso criado
     //em atualizações apenas retorna-se a entidade atualizada e 200 ok no maximo
        return ResponseEntity.ok().body(atualizado);
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