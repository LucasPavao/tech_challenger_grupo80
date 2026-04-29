package br.com.tech.challenger.api_restaurante.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserAddressRequestDTO(

        @Schema(description = "Address street name", example = "Paulista Avenue")
        @NotBlank(message = "Street is required")
        String street,

        @Schema(description = "Address city", example = "Sao Paulo")
        @NotBlank(message = "City is required")
        String city,

        @Schema(description = "Address number", example = "1000")
        String number,

        @Schema(description = "Address complement", example = "Suite 12")
        String complement,

        @Schema(description = "Address state", example = "SP")
        @NotBlank(message = "State is required")
        String state,

        @Schema(description = "Address ZIP code", example = "01310-100")
        @NotBlank(message = "ZIP code is required")
        String zipCode,

        @Schema(description = "Address country", example = "Brazil")
        @NotBlank(message = "Country is required")
        String country
) {}