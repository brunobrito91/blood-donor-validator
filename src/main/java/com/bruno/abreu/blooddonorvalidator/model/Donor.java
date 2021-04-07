package com.bruno.abreu.blooddonorvalidator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donor {

    private Long id;
    private String nome;
    @Id
    private String cpf;
    private String rg;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nasc;
    private Sexo sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone_fixo;
    private String celular;
    private Double altura;
    private Integer peso;
    private TipoSanguineo tipo_sanguineo;
}
