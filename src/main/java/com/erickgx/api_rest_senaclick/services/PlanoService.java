package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.dtos.plano.requests.PlanoRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.plano.responses.PlanoResponseDTO;
import com.erickgx.api_rest_senaclick.exceptions.DuplicateClasseException;
import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.mappers.PlanoMapper;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {


    private final PlanoRepository planoRepository;
    private final PlanoMapper mapper;

    public PlanoService(PlanoRepository planoRepository, PlanoMapper mapper) {
        this.planoRepository = planoRepository;
        this.mapper = mapper;
    }

    //verificar se um plano existe
    public Plano obterPlanoPorId(Long idPlano){

        return planoRepository.findById(idPlano)
                .orElseThrow(() ->new ResourceNotFoundException("Plano com o ID: " +idPlano+" não existe"));
    }

    public PlanoResponseDTO cadastrarPlano (PlanoRequestDTO dto){

            Plano plano  = mapper.toEntity(dto);

            if (planoRepository.existsByClasse(plano.getClasse())){
                    throw new DuplicateClasseException("Já existe uma Classe com este nome");
            }

            Plano response = planoRepository.save(plano);

            return mapper.toResponse(response);

    }


    public List<PlanoResponseDTO> listarTodosPlanos(){

        return planoRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public PlanoResponseDTO buscarPlanoPorId(Long idPlano){

       Plano plano =  planoRepository.findById(idPlano)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado Plano com ID: "+idPlano));

       return mapper.toResponse(plano);

    }

    public void deletarPlanoPorId(Long idPlano){
            planoRepository.findById(idPlano)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado Plano com ID: "+idPlano+" para excluir"));
            planoRepository.deleteById(idPlano);
    }


        //necessario corrigir, e settar novos campos da entidade recuperada do bd com os da dto atualizados
//    public PlanoResponseDTO atualizarPlano(Long idPlano , PlanoRequestDTO dto){
//
//        Plano plano =  planoRepository.findById(idPlano)
//                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: "+idPlano+" para atualizar"));
//
//        plano.setId(idPlano);
//        //mesclagem dos novos valores de campos do front=end com a entidade achada no estado anterior
//        plano = mapper.toEntity(dto);
//
//
//        //atualização da entidade atualizada com os novos campos
//        Plano atualizado = planoRepository.save(plano);
//
//        //conversão da entidade atualizada para a dto de resposta de atualizaçao
//        return mapper.toResponse(atualizado);
//
//    }
}
