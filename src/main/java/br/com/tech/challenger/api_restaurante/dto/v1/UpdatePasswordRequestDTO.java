package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO (

        @Schema(description = "New user access password", example = "NewStrongPass@123")
        @NotBlank(message = "Password is required")
        String newPassword
) {}
