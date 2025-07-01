package com.erickgx.api_rest_senaclick.mappers;

import com.erickgx.api_rest_senaclick.dtos.cliente.requests.ClienteRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.cliente.responses.ClienteResponseDTO;
import com.erickgx.api_rest_senaclick.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    //Convertendo a requisição JSON para o dto clienteRequest e Convertento em cliente
    //para transportar os dados para a camada de serviço
    public static Cliente toEntity(ClienteRequestDTO dto){ //convençao de nomemclatura
        Cliente cliente =  new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        return cliente;
    }

    //Conversao dos dados do BD que é a entity Cliente para o dto Response para o frontend
    public static ClienteResponseDTO toResponse(Cliente cliente){
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        return  response;
    }

}
