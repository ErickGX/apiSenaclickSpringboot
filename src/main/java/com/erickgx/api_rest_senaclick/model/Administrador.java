package com.erickgx.api_rest_senaclick.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Administrador extends Usuario {

    @NotBlank
    private String nivel;

}
