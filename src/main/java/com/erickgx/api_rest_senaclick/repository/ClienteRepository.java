package com.erickgx.api_rest_senaclick.repository;

import com.erickgx.api_rest_senaclick.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
