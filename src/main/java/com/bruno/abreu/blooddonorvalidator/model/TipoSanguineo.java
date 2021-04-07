package com.bruno.abreu.blooddonorvalidator.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoSanguineo {
    AMAIS("A+"), AMENOS("A-"), BMAIS("B+"), BMENOS("B-"), ABMAIS("AB+"), ABMENOS("AB-"), OMAIS("O+"), OMENOS("O-");

    @JsonValue
    private final String tipoSanguineo;
}
