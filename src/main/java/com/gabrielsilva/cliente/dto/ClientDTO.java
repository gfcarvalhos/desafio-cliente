package com.gabrielsilva.cliente.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielsilva.cliente.entities.Client;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Nome requerido")
    private String name;
    private String cpf;
    @PositiveOrZero(message = "O valor deve ser positivo ou zero")
    private Double income;
    @Past(message = "Data inválida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @PositiveOrZero(message = "O valor deve ser positivo ou zero" )
    private Integer children;

    public ClientDTO() {}

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
