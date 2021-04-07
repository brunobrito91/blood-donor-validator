package com.bruno.abreu.blooddonorvalidator.result;

import com.bruno.abreu.blooddonorvalidator.model.Sexo;
import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DonorResult {

    private Map<String, Long> candidatosPorEstado;
    private Map<Integer, Double> imcMedioPorFaixaEtaria;
    private Map<Sexo, Long> percentualObesos;
    private Map<TipoSanguineo, Double> idadePorTipoSanguineo;
    private Map<TipoSanguineo, Long> quantidadeDoadoresParaReceptor;

}
