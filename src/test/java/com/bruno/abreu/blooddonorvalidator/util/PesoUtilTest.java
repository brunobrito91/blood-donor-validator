package com.bruno.abreu.blooddonorvalidator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PesoUtilTest {

    @Test
    void testGetImc(){
        Integer peso = 95;
        Double altura = 1.76;

        Double imc = PesoUtil.getImc(peso, altura);
        assertEquals(30.668904958677686, imc);
    }
}
