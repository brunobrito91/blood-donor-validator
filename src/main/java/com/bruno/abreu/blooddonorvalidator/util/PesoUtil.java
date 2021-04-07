package com.bruno.abreu.blooddonorvalidator.util;

public class PesoUtil {
    public static Double getImc(Integer peso, Double altura) {
        return peso / Math.pow(altura, 2);
    }
}
