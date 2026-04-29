package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

public record AuthenticationTokenDetailsDTO(
        @Schema(description = "JWT access token used to authenticate requests", example = "eyJhbGciOiJIUzI1NiJ9.accessTokenPayload.signature")
        String accessToken,
        @Schema(description = "Refresh token used to renew the access token", example = "eyJhbGciOiJIUzI1NiJ9.refreshTokenPayload.signature")
        String refreshToken,
        @Schema(description = "Access token expiration date and time", example = "2026-12-31T23:59:59Z")
        Instant expiresAt
) {
}
