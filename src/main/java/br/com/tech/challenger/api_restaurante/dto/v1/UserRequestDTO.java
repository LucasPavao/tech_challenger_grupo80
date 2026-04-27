package br.com.tech.challenger.api_restaurante.dto.v1;

import br.com.tech.challenger.api_restaurante.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(

        @Schema(description = "User full name", example = "John Smith")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "User email", example = "john.smith@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @Schema(description = "Unique login for authentication", example = "john.smith")
        @NotBlank(message = "Login is required")
        String login,

        @Schema(description = "User access password", example = "StrongPass@123")
        @NotBlank(message = "Password is required")
        String password,

        @Schema(description = "User type in the system", example = "CUSTOMER")
        @NotNull(message = "User type is required")
        UserType userType,

        @Schema(description = "User address data", example = "{\"street\":\"Paulista Avenue\",\"city\":\"Sao Paulo\",\"number\":\"1000\",\"complement\":\"Suite 12\",\"state\":\"SP\",\"zipCode\":\"01310-100\",\"country\":\"Brazil\"}")
        @NotNull(message = "User must be registered with an address")
        @Valid
        UserAddressRequestDTO address
) {}