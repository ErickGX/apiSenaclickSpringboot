package com.erickgx.api_rest_senaclick.mappers;

import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteSaveDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteUpdateDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteListResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteUpdatedDTO;
import com.erickgx.api_rest_senaclick.model.Cliente;
import org.springframework.stereotype.Component;

@Component //para a bean ser gerenciada pelo spring e poder ser Injetada automt
public class ClienteMapper {

    //Convertendo a requisição JSON para o dto clienteRequest e Convertento em cliente
    //para transportar os dados para a camada de serviço
    public Cliente toEntity(ClienteSaveDTO dto){ //convençao de nomemclatura
        Cliente cliente =  new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        return cliente;
    }

    //Conversao dos dados do BD que é a entity Cliente para o dto Response para o frontend
    public ClienteResponseDTO toResponse(Cliente cliente){
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        return  response;
    }

    public void updateClienteFromDto(ClienteUpdateDTO dto, Cliente cliente){ //pega o cliente pelo id no bd e aplica os campos atualzados
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
    }

    public ClienteUpdatedDTO toUpdatedDto(Cliente cliente){
        ClienteUpdatedDTO dto = new ClienteUpdatedDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setSobrenome(cliente.getSobrenome());
        return dto;
    }

    public ClienteListResponseDTO toListDto(Cliente cliente){
        ClienteListResponseDTO dto = new ClienteListResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setSobrenome(cliente.getSobrenome());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

}
