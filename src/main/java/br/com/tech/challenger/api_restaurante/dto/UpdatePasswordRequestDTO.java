package br.com.tech.challenger.api_restaurante.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO (
        @NotBlank(message = "Senha é obrigatória")
        String newPassword)
{}
