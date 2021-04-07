package com.bruno.abreu.blooddonorvalidator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;


public class DateUtilTest {

    @Test
    void testGetIdade() {
        //GIVEN
        LocalDate dataNascimento = LocalDate.of(1991, Month.FEBRUARY, 25);
        LocalDate dataAtual = LocalDate.of(2011, Month.FEBRUARY, 25);

        MockedStatic<DateUtil> dateUtilMockedStatic = mockStatic(DateUtil.class);
        dateUtilMockedStatic.when(DateUtil::getDiaAtual).thenReturn(dataAtual);
        dateUtilMockedStatic.when(() -> DateUtil.getIdade(dataNascimento)).thenCallRealMethod();

        //WHEN
        Integer idade = DateUtil.getIdade(dataNascimento);

        //THEN
        Assertions.assertEquals(20, idade);
        dateUtilMockedStatic.close();
    }

    @Test
    void testGetFaixaEtaria() {
        //GIVEN
        int idade = 0;
        //WHEN
        Integer faixaEtaria = DateUtil.getFaixaEtaria(idade);
        //THEN
        Assertions.assertEquals(1, faixaEtaria);


        idade = 10;
        faixaEtaria = DateUtil.getFaixaEtaria(idade);

        Assertions.assertEquals(1, faixaEtaria);

        idade = 11;
        faixaEtaria = DateUtil.getFaixaEtaria(idade);

        Assertions.assertEquals(2, faixaEtaria);

        idade = 20;
        faixaEtaria = DateUtil.getFaixaEtaria(idade);

        Assertions.assertEquals(2, faixaEtaria);

        idade = 21;
        faixaEtaria = DateUtil.getFaixaEtaria(idade);

        Assertions.assertEquals(3, faixaEtaria);

        idade = 30;
        faixaEtaria = DateUtil.getFaixaEtaria(idade);

        Assertions.assertEquals(3, faixaEtaria);
    }

    @Test
    void testLocalDateParse(){
        LocalDate localDateParsed = LocalDate.parse("23/05/1964", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Assertions.assertEquals(LocalDate.of(1964, 5, 23), localDateParsed);
    }
}
