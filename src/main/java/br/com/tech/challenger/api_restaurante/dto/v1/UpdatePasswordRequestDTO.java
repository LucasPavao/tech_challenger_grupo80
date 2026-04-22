package br.com.tech.challenger.api_restaurante.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO (

        @NotBlank(message = "Senha é obrigatória")
        String newPassword
) {}
