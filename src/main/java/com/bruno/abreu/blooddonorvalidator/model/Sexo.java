package com.bruno.abreu.blooddonorvalidator.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sexo {
    FEMININO("Feminino"), MASCULINO("Masculino");

    @JsonValue
    private final String sexo;
}
