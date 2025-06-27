package com.erickgx.api_rest_senaclick.services;


import com.erickgx.api_rest_senaclick.exceptions.ResourceNotFoundException;
import com.erickgx.api_rest_senaclick.model.Plano;
import com.erickgx.api_rest_senaclick.repository.PlanoRepository;
import org.springframework.stereotype.Service;

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
}
