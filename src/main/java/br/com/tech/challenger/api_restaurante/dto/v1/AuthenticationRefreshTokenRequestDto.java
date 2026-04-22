package br.com.tech.challenger.api_restaurante.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRefreshTokenRequestDto(

        @NotBlank(message = "O token de recuperação é obrigatório")
        String refreshToken
) {
}
