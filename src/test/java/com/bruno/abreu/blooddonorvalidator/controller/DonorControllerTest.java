package com.bruno.abreu.blooddonorvalidator.controller;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;
import com.bruno.abreu.blooddonorvalidator.result.DonorResult;
import com.bruno.abreu.blooddonorvalidator.service.DonorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DonorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DonorService donorService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void saveDonorShouldReturnStatusCreated() throws Exception {
        List<Donor> donors = new ArrayList<>();
        donors.add(Donor.builder()
                .data_nasc(LocalDate.of(1952, Month.JANUARY, 1))
                .tipo_sanguineo(TipoSanguineo.AMAIS)
                .build());

        DonorResult donorResult = DonorResult.builder().build();

        when(donorService.fetchDonorResult(donors)).thenReturn(donorResult);

        String content = objectMapper.writeValueAsString(donors);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/donor/batch")
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void saveDonorWithLocalDateAsBirthDateShouldReturnStatusCreated() throws Exception {
        List<Donor> donors = new ArrayList<>();
        donors.add(Donor.builder()
                .data_nasc(LocalDate.of(1952, Month.JANUARY, 1))
                .build());

        DonorResult donorResult = DonorResult.builder().build();

        when(donorService.fetchDonorResult(donors)).thenReturn(donorResult);

        String content = "[{\"id\":null,\"nome\":null,\"cpf\":null,\"rg\":null,\"data_nasc\":\"23/05/1964\",\"sexo\":null,\"mae\":null,\"pai\":null,\"email\":null,\"cep\":null,\"endereco\":null,\"numero\":null,\"bairro\":null,\"cidade\":null,\"estado\":null,\"telefone_fixo\":null,\"celular\":null,\"altura\":null,\"peso\":null,\"tipo_sanguineo\":\"A+\"}]";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/donor/batch")
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
