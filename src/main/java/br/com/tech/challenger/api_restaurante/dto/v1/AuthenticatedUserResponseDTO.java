package br.com.tech.challenger.api_restaurante.dto.v1;

public record AuthenticatedUserResponseDTO(
    UserResponseDTO user,
    AuthenticationTokenDetailsDTO token
) {
}
