package br.com.tech.challenger.api_restaurante.dto.v1;

import java.time.Instant;

public record AuthenticationTokenDetailsDTO(
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {
}
