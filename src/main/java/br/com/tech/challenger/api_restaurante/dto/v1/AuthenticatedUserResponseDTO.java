package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticatedUserResponseDTO(
    @Schema(description = "Authenticated user information (id, name, email, and profile)", example = "{\"id\":1,\"name\":\"John Smith\",\"email\":\"john.smith@example.com\",\"login\":\"john.smith\",\"userType\":\"CUSTOMER\"}")
    UserResponseDTO user,
    @Schema(description = "Authentication token details (JWT token, type, and expiration)", example = "{\"accessToken\":\"eyJhbGciOiJIUzI1NiJ9.accessTokenPayload.signature\",\"refreshToken\":\"eyJhbGciOiJIUzI1NiJ9.refreshTokenPayload.signature\",\"expiresAt\":\"2026-12-31T23:59:59Z\"}")
    AuthenticationTokenDetailsDTO token
) {
}
