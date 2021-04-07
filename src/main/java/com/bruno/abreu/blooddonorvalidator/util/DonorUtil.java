package com.bruno.abreu.blooddonorvalidator.util;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.model.Sexo;
import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bruno.abreu.blooddonorvalidator.model.Sexo.FEMININO;
import static com.bruno.abreu.blooddonorvalidator.model.Sexo.MASCULINO;
import static com.bruno.abreu.blooddonorvalidator.util.DateUtil.getFaixaEtaria;
import static com.bruno.abreu.blooddonorvalidator.util.DateUtil.getIdade;
import static com.bruno.abreu.blooddonorvalidator.util.PesoUtil.getImc;

public class DonorUtil {

    private static Logger logger = LoggerFactory.getLogger(DonorUtil.class);

    public static Map<String, Long> getCandidatosPorEstado(List<Donor> donors) {
        logger.info("Total Candidatos: " + donors.size());
        return donors.stream()
                .collect(Collectors.groupingBy(Donor::getEstado, Collectors.counting()));
    }

    public static Map<Integer, Double> getImcMedioPorFaixaEtaria(List<Donor> donors) {
        return donors.stream()
                .map(donor -> {
                    Integer idade = getIdade(donor.getData_nasc());
                    Integer faixaEtaria = getFaixaEtaria(idade);
                    Double imc = getImc(donor.getPeso(), donor.getAltura());
                    logger.info(String.format("IMC do %s é igual %s", donor.getNome(), imc));
                    return Pair.of(faixaEtaria, imc);
                })
                .collect(Collectors.groupingBy(Pair::getFirst, Collectors.averagingDouble(Pair::getSecond)));
    }


    public static Map<Sexo, Long> getPercentualObesos(List<Donor> donors) {
        Map<Sexo, Long> percentualObesosPorSexo = Map.of(Sexo.FEMININO, 0L, MASCULINO, 0L);
        Map<Sexo, Long> totalPorSexo = donors.stream()
                .collect(Collectors.groupingBy(Donor::getSexo, Collectors.counting()));
        logger.info("Total de pessoas do sexo feminino: " + totalPorSexo.get(FEMININO));
        logger.info("Total de pessoas do sexo masculino: " + totalPorSexo.get(MASCULINO));

        Map<Sexo, Long> obesosPorSexo = donors.stream()
                .filter(donor -> getImc(donor.getPeso(), donor.getAltura()) > 30)
                .collect(Collectors.groupingBy(Donor::getSexo, Collectors.counting()));
        logger.info("Total de obesos do sexo feminino: " + obesosPorSexo.get(FEMININO));
        logger.info("Total de obesos do sexo masculino: " + obesosPorSexo.get(MASCULINO));

        if (obesosPorSexo.keySet().isEmpty()) {
            return percentualObesosPorSexo;
        }

        percentualObesosPorSexo = obesosPorSexo.entrySet().stream()
                .map(entryObesosPorSexo ->
                        Pair.of(entryObesosPorSexo.getKey(), calcularPorcentagem(entryObesosPorSexo.getValue(), totalPorSexo.get(entryObesosPorSexo.getKey()))))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        if (percentualObesosPorSexo.keySet().size() < 2) {
            if (percentualObesosPorSexo.containsKey(FEMININO)) {
                percentualObesosPorSexo.put(MASCULINO, 0L);
            } else if (percentualObesosPorSexo.containsKey(MASCULINO)) {
                percentualObesosPorSexo.put(FEMININO, 0L);
            }
        }
        return percentualObesosPorSexo;
    }

    private static Long calcularPorcentagem(Long value, Long total) {
        return value * 100 / total;
    }

    public static Map<TipoSanguineo, Double> getMediaIdadePorTipoSanguineo(List<Donor> donors) {
        return donors.stream()
                .map(donor -> Pair.of(donor.getTipo_sanguineo(), getIdade(donor.getData_nasc())))
                .collect(Collectors.groupingBy(Pair::getFirst, Collectors.averagingDouble(Pair::getSecond)));
    }

    public static Map<TipoSanguineo, Long> getQuantidadeDoadoresParaReceptor(List<Donor> donors) {
        Map<TipoSanguineo, Long> result = new HashMap<>();
        donors.stream()
                .filter(donor -> donor.getPeso() > 50 && getIdade(donor.getData_nasc()) >= 16 && getIdade(donor.getData_nasc()) <= 69)
                .map(donor -> getReceptores(donor.getTipo_sanguineo()))
                .forEach(tipoSanguineoIntegerMap -> tipoSanguineoIntegerMap.forEach((key, value) -> {
                    Long cont = 0L;
                    if (result.containsKey(key)) {
                        cont = result.get(key);
                    }
                    result.put(key, value + cont);
                }));

        return result;
    }

    private static Map<TipoSanguineo, Long> getReceptores(TipoSanguineo tipo_sanguineo) {
        switch (tipo_sanguineo) {
            case AMAIS:
                return Map.of(TipoSanguineo.ABMAIS, 1L, TipoSanguineo.AMAIS, 1L);
            case AMENOS:
                return Map.of(TipoSanguineo.AMAIS, 1L, TipoSanguineo.AMENOS, 1L, TipoSanguineo.ABMAIS, 1L, TipoSanguineo.ABMENOS, 1L);
            case BMAIS:
                return Map.of(TipoSanguineo.BMAIS, 1L, TipoSanguineo.ABMAIS, 1L);
            case BMENOS:
                return Map.of(TipoSanguineo.BMAIS, 1L, TipoSanguineo.BMENOS, 1L, TipoSanguineo.ABMAIS, 1L, TipoSanguineo.ABMENOS, 1L);
            case ABMAIS:
                return Map.of(TipoSanguineo.ABMAIS, 1L);
            case ABMENOS:
                return Map.of(TipoSanguineo.ABMAIS, 1L, TipoSanguineo.ABMENOS, 1L);
            case OMAIS:
                return Map.of(TipoSanguineo.AMAIS, 1L, TipoSanguineo.BMAIS, 1L, TipoSanguineo.OMAIS, 1L, TipoSanguineo.ABMAIS, 1L);
            case OMENOS:
                return Map.of(TipoSanguineo.AMAIS, 1L, TipoSanguineo.BMAIS, 1L, TipoSanguineo.OMAIS, 1L, TipoSanguineo.ABMAIS, 1L, TipoSanguineo.AMENOS, 1L, TipoSanguineo.BMENOS, 1L, TipoSanguineo.OMENOS, 1L, TipoSanguineo.ABMENOS, 1L);
            default:
                return Map.of();
        }
    }
}
