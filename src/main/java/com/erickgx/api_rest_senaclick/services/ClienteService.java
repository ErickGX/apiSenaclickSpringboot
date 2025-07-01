package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteUpdateDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteListResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteUpdatedDTO;
import com.erickgx.api_rest_senaclick.exceptions.DuplicateEmailException;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.mappers.ClienteMapper;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ClienteListResponseDTO> listarTodosClientes(){

        return clienterepository.findAll().stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
    }

    public ClienteListResponseDTO buscarClientePorId(Long idCliente){
      Cliente cliente =  clienterepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idCliente));

      return mapper.toListDto(cliente);

    }

    public void deletarClientePorId(Long idCliente){
        clienterepository.findById(idCliente)
              .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idCliente+" para excluir"));
          clienterepository.deleteById(idCliente);
    }

    public ClienteUpdatedDTO atualizarClientePartial(Long idCliente , ClienteUpdateDTO dto){

        Cliente cliente = clienterepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: "+idCliente+" para atualizar"));

        //mesclagem dos novos valores de campos do front=end com a entidade achada no estado anterior com
        mapper.updateClienteFromDto(dto, cliente);

        //atualização da entidade atualizada com os novos campos e com id setado para atualizar
        Cliente atualizado = clienterepository.save(cliente);

        //conversão da entidade atualizada para a dto de resposta de atualizaçao
        return mapper.toUpdatedDto(cliente);

    }
}
