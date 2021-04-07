package com.bruno.abreu.blooddonorvalidator.util;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.model.Sexo;
import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bruno.abreu.blooddonorvalidator.model.Sexo.FEMININO;
import static com.bruno.abreu.blooddonorvalidator.model.Sexo.MASCULINO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DonorUtilTest {

    private static final String SP = "SP";
    private static final String MG = "MG";
    private static final String RJ = "RJ";

    @Test
    void test3CandidatosDeSP(){
        //GIVEN
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .estado(SP)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .estado(SP)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .estado(SP)
                .build();
        donors.add(donor3);

        //WHEN
        Map<String, Long> candidatosPorEstado = DonorUtil.getCandidatosPorEstado(donors);

        //THEN
        assertEquals(3, candidatosPorEstado.get(SP));
    }

    @Test
    void test3CandidatosCadaUmDeUmLugar(){
        //GIVEN
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .estado(SP)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .estado(MG)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .estado(RJ)
                .build();
        donors.add(donor3);

        //WHEN
        Map<String, Long> candidatosPorEstado = DonorUtil.getCandidatosPorEstado(donors);

        //THEN
        assertEquals(1, candidatosPorEstado.get(SP));
        assertEquals(1, candidatosPorEstado.get(MG));
        assertEquals(1, candidatosPorEstado.get(RJ));
    }

    @Test
    void testImcMedioPara3PessoasDeFaixasEtariasDiferentes(){
        //GIVEN
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .data_nasc(LocalDate.of(2011, Month.JANUARY, 1))
                .peso(10)
                .altura(1.60)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .data_nasc(LocalDate.of(2001, Month.JANUARY, 1))
                .peso(20)
                .altura(1.60)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .peso(30)
                .altura(1.60)
                .build();
        donors.add(donor3);

        //WHEN
        Map<Integer, Double> imcMedioPorFaixaEtaria = DonorUtil.getImcMedioPorFaixaEtaria(donors);

        //THEN
        assertEquals(3.906249999999999, imcMedioPorFaixaEtaria.get(1));
        assertEquals(7.812499999999998, imcMedioPorFaixaEtaria.get(2));
        assertEquals(11.718749999999998, imcMedioPorFaixaEtaria.get(3));
    }

    @Test
    void testImcMedioPara3PessoasDeFaixasEtariasIguais(){
        //GIVEN
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .peso(10)
                .altura(1.60)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .peso(20)
                .altura(1.60)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .peso(30)
                .altura(1.60)
                .build();
        donors.add(donor3);

        //WHEN
        Map<Integer, Double> imcMedioPorFaixaEtaria = DonorUtil.getImcMedioPorFaixaEtaria(donors);

        //THEN
        assertEquals(7.812499999999999, imcMedioPorFaixaEtaria.get(3));

    }

    @Test
    void testPercentualObesosEntreHomensEMulheresCom50PorCento(){
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .peso(121)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .peso(121)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor3);

        Donor donor4 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor4);

        Map<Sexo, Long> percentualObesos = DonorUtil.getPercentualObesos(donors);
        assertEquals(50, percentualObesos.get(FEMININO));
        assertEquals(50, percentualObesos.get(MASCULINO));
    }

    @Test
    void testPercentualObesosEntreHomensEMulheresCom0PorCento(){
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor3);

        Donor donor4 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor4);

        Map<Sexo, Long> percentualObesos = DonorUtil.getPercentualObesos(donors);
        assertEquals(0, percentualObesos.get(FEMININO));
        assertEquals(0, percentualObesos.get(MASCULINO));
    }

    @Test
    void testPercentualObesosHomensCom0PorCentoMulheresCom50PorCento(){
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .peso(121)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(FEMININO)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor3);

        Donor donor4 = Donor.builder()
                .peso(120)
                .altura(2.0)
                .sexo(MASCULINO)
                .build();
        donors.add(donor4);

        Map<Sexo, Long> percentualObesos = DonorUtil.getPercentualObesos(donors);
        assertEquals(50, percentualObesos.get(FEMININO));
        assertEquals(0, percentualObesos.get(MASCULINO));
    }

    @Test
    void testIdadePorTipoSanguineo(){
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .tipo_sanguineo(TipoSanguineo.AMAIS)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .data_nasc(LocalDate.of(2001, Month.JANUARY, 1))
                .tipo_sanguineo(TipoSanguineo.AMAIS)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .data_nasc(LocalDate.of(1991, Month.JANUARY, 1))
                .tipo_sanguineo(TipoSanguineo.AMENOS)
                .build();
        donors.add(donor3);

        Donor donor4 = Donor.builder()
                .data_nasc(LocalDate.of(2001, Month.JANUARY, 1))
                .tipo_sanguineo(TipoSanguineo.AMENOS)
                .build();
        donors.add(donor4);

        Map<TipoSanguineo, Double> mediaIdadePorTipoSanguineo = DonorUtil.getMediaIdadePorTipoSanguineo(donors);

        assertEquals(25, mediaIdadePorTipoSanguineo.get(TipoSanguineo.AMENOS));
        assertEquals(25, mediaIdadePorTipoSanguineo.get(TipoSanguineo.AMAIS));
    }

    @Test
    void testQuantidadeDoadoresParaCadaReceptor(){
        List<Donor> donors = new ArrayList<>();
        Donor donor1 = Donor.builder()
                .data_nasc(LocalDate.of(1952, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.AMAIS)
                .build();
        donors.add(donor1);

        Donor donor2 = Donor.builder()
                .data_nasc(LocalDate.of(1952, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.AMENOS)
                .build();
        donors.add(donor2);

        Donor donor3 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.BMAIS)
                .build();
        donors.add(donor3);

        Donor donor4 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.BMENOS)
                .build();
        donors.add(donor4);

        Donor donor5 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.ABMAIS)
                .build();
        donors.add(donor5);

        Donor donor6 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.ABMENOS)
                .build();
        donors.add(donor6);

        Donor donor7 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.OMAIS)
                .build();
        donors.add(donor7);

        Donor donor8 = Donor.builder()
                .data_nasc(LocalDate.of(2005, Month.JANUARY, 1))
                .peso(51)
                .tipo_sanguineo(TipoSanguineo.OMENOS)
                .build();
        donors.add(donor8);

        Map<TipoSanguineo, Long> quantidadeDoadoresParaCadaTipoSanguineoReceptor =
                DonorUtil.getQuantidadeDoadoresParaReceptor(donors);
        assertEquals(4L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.AMAIS));
        assertEquals(2L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.AMENOS));
        assertEquals(4L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.BMAIS));
        assertEquals(2L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.BMENOS));
        assertEquals(8L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.ABMAIS));
        assertEquals(4L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.ABMENOS));
        assertEquals(2L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.OMAIS));
        assertEquals(1L, quantidadeDoadoresParaCadaTipoSanguineoReceptor.get(TipoSanguineo.OMENOS));
    }

}
