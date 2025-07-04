package com.erickgx.api_rest_senaclick.repository;

import com.erickgx.api_rest_senaclick.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlanoRepository extends JpaRepository<Plano, Long> {
    boolean existsById(Long idPlano);
    Boolean existsByClasse(String classe);
}
