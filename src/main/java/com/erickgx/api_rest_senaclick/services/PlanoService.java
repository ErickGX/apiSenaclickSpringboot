package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.model.Cliente;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {


    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    //verificar se um plano existe
    public Plano obterPlanoPorId(Long idPlano){

        return planoRepository.findById(idPlano)
                .orElseThrow(() ->new ResourceNotFoundException("Plano com o ID: " +idPlano+" não existe"));
    }

    public Plano cadastrarPlano (Plano novoPlano){
        return planoRepository.save(novoPlano);
    }


    //Regra de negocio pode ter lista vazia
    public List<Plano> listarTodosPlanos(){
        return planoRepository.findAll();
    }

    public Plano buscarPlanoPorId(Long idPlano){
        return planoRepository.findById(idPlano)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idPlano));

    }

    public void deletarPlanoPorId(Long idPlano){
            planoRepository.findById(idPlano)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado cliente com ID: "+idPlano+" para excluir"));

        planoRepository.deleteById(idPlano);
    }
}
