package com.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPetsDto(

        @NotBlank(message = "Raca obrigatoria")
        String raca,

        @NotNull(message = "Idade do pet obrigatoria")
        String idade) {
}
