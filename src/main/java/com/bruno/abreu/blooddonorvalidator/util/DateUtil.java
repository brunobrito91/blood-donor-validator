package com.bruno.abreu.blooddonorvalidator.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    public static Integer getFaixaEtaria(Integer idade) {
        if (idade > 0) {
            int val1 = idade / 10;
            int val2 = idade % 10;
            return val2 > 0 ? val1 + 1 : val1;
        }
        return 1;
    }

    public static Integer getIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = DateUtil.getDiaAtual();
        Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }

    public static LocalDate getDiaAtual() {
        return LocalDate.now();
    }
}
