package com.example.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public record DadosCadastroDto(

        @NotBlank(message = "Nome obrigatorio")
        String nome,

        @NotNull(message = "Cpf obrigatorio")
        String cpf,

        @NotBlank(message = "Email obrigatorio")
        @Email(message = "Email obrigatorio")
        String email) {
}
