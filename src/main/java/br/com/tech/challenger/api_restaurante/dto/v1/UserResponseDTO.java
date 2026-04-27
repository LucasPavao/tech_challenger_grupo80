package br.com.tech.challenger.api_restaurante.dto.v1;

import br.com.tech.challenger.api_restaurante.entity.User;
import br.com.tech.challenger.api_restaurante.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserResponseDTO(
        @Schema(description = "Unique user identifier", example = "1")
        Long id,
        @Schema(description = "User full name", example = "John Smith")
        String name,
        @Schema(description = "User email", example = "john.smith@example.com")
        String email,
        @Schema(description = "User unique login", example = "john.smith")
        String login,
        @Schema(description = "User registered address", example = "{\"id\":10,\"street\":\"Paulista Avenue\",\"city\":\"Sao Paulo\",\"number\":\"1000\",\"complement\":\"Suite 12\",\"state\":\"SP\",\"zipCode\":\"01310-100\",\"country\":\"Brazil\"}")
        UserAddressResponseDTO address,
        @Schema(description = "User type in the system", example = "CUSTOMER")
        UserType userType,
        @Schema(description = "Date and time of the last profile update", example = "2026-04-23T10:15:30")
        LocalDateTime lastModifiedDate
) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                UserAddressResponseDTO.fromEntity(user.getUserAddress()),
                user.getUserType(),
                user.getLastModifiedDate()
        );
    }
}