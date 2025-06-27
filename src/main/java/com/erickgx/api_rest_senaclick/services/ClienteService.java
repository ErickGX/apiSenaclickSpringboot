package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.enums.TipoPagamento;
import com.erickgx.api_rest_senaclick.exceptions.DuplicateEmailException;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository Clienterepository;

    public ClienteService(ClienteRepository Clienterepository) {
        this.Clienterepository = Clienterepository;
    }

    public Cliente cadastrarCliente(Cliente cliente){

        if (Clienterepository.existsByEmail(cliente.getEmail())){
            throw new DuplicateEmailException("Já existe um cliente com esse e-mail");
        }

        // Cadastro novo cliente
        Cliente novoCliente = Clienterepository.save(cliente);

        return novoCliente;
    }
}
