package com.example.dtos;

import java.time.LocalDateTime;

public record DadosCadastroAdocao(Long id, Long idAdotante, Long idPets, LocalDateTime dataAdocao) {
}
