package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRefreshTokenRequestDto(

        @Schema(description = "Refresh token used to generate a new access token", example = "eyJhbGciOiJIUzI1NiJ9.refreshTokenPayload.signature")
        @NotBlank(message = "Refresh token is required")
        String refreshToken
) {
}
