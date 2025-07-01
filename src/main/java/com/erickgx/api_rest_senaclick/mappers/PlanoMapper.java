package com.erickgx.api_rest_senaclick.mappers;

import com.erickgx.api_rest_senaclick.dtos.plano.requests.PlanoRequestDTO;
import com.erickgx.api_rest_senaclick.dtos.plano.responses.PlanoResponseDTO;
import com.erickgx.api_rest_senaclick.model.Plano;
import org.springframework.stereotype.Component;

@Component
public class PlanoMapper {

    public Plano toEntity(PlanoRequestDTO dto) {
        Plano plano = new Plano();
        plano.setTitulo(dto.getTitulo());
        plano.setPreco(dto.getPreco());
        plano.setArtigo(dto.getArtigo());
        plano.setNoticia(dto.getNoticia());
        plano.setEdicao(dto.getEdicao());
        plano.setTexto_botao(dto.getTexto_botao());
        plano.setClasse(dto.getClasse());
        return plano;
    }

    public PlanoResponseDTO toResponse(Plano plano) {
        PlanoResponseDTO dto = new PlanoResponseDTO();
        dto.setId(plano.getId());
        dto.setTitulo(plano.getTitulo());
        dto.setPreco(plano.getPreco());
        dto.setArtigo(plano.getArtigo());
        dto.setNoticia(plano.getNoticia());
        dto.setEdicao(plano.getEdicao());
        dto.setTexto_botao(plano.getTexto_botao());
        dto.setClasse(plano.getClasse());
        return dto;
    }
}
