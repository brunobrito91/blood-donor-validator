package com.bruno.abreu.blooddonorvalidator.service;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.result.DonorResult;
import com.bruno.abreu.blooddonorvalidator.util.DonorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    public DonorResult fetchDonorResult(List<Donor> donors) {

        return DonorResult.builder()
                .candidatosPorEstado(DonorUtil.getCandidatosPorEstado(donors))
                .imcMedioPorFaixaEtaria(DonorUtil.getImcMedioPorFaixaEtaria(donors))
                .percentualObesos(DonorUtil.getPercentualObesos(donors))
                .idadePorTipoSanguineo(DonorUtil.getMediaIdadePorTipoSanguineo(donors))
                .quantidadeDoadoresParaReceptor(DonorUtil.getQuantidadeDoadoresParaReceptor(donors))
                .build();
    }
}
