package br.com.tech.challenger.api_restaurante.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
        @NotBlank(message = "Login é obrigatório")
        String login,

        @NotBlank(message = "Senha é obrigatório")
        String password
) {
}
