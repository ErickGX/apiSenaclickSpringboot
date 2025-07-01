package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.exceptions.DuplicateEmailException;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.mappers.ClienteMapper;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienterepository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository clienterepository, ClienteMapper mapper) {
        this.clienterepository = clienterepository;
        this.mapper = mapper;
    }

    public Cliente cadastrarCliente(Cliente cliente){

        if (clienterepository.existsByEmail(cliente.getEmail())){
            throw new DuplicateEmailException("Já existe um cliente com esse e-mail");
        }

        // Cadastro novo cliente
        Cliente novoCliente = clienterepository.save(cliente);

        return novoCliente;
    }

    //Regra de negocio pode ter lista vazia , caso não , Lançar exceptions
    public List<Cliente> listarTodosClientes(){
        return clienterepository.findAll();
    }

    public Cliente buscarClientePorId(Long idCliente){
        return clienterepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idCliente));

    }

    public void deletarClientePorId(Long idCliente){
      Cliente cliente =  clienterepository.findById(idCliente)
              .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idCliente+" para excluir"));

       clienterepository.deleteById(idCliente);
    }
}
