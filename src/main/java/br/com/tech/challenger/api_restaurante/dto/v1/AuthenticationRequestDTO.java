package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
        @Schema(description = "User login for authentication", example = "john.smith")
        @NotBlank(message = "Login is required")
        String login,

        @Schema(description = "User access password", example = "StrongPass@123")
        @NotBlank(message = "Password is required")
        String password
) {
}
