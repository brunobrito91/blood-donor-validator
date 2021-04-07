package com.bruno.abreu.blooddonorvalidator.service;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.repository.DonorRepository;
import com.bruno.abreu.blooddonorvalidator.result.DonorResult;
import com.bruno.abreu.blooddonorvalidator.util.DonorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    @Autowired
    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public DonorResult fetchDonorResult(List<Donor> donors) {

        return DonorResult.builder()
                .candidatosPorEstado(DonorUtil.getCandidatosPorEstado(donors))
                .imcMedioPorFaixaEtaria(DonorUtil.getImcMedioPorFaixaEtaria(donors))
                .percentualObesos(DonorUtil.getPercentualObesos(donors))
                .idadePorTipoSanguineo(DonorUtil.getMediaIdadePorTipoSanguineo(donors))
                .quantidadeDoadoresParaReceptor(DonorUtil.getQuantidadeDoadoresParaReceptor(donors))
                .build();
    }

    public void save(List<Donor> donors){
        donorRepository.saveAll(donors);
    }
}
