package br.com.tech.challenger.api_restaurante.dto.v1;

import br.com.tech.challenger.api_restaurante.entity.UserAddress;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserAddressResponseDTO(
        @Schema(description = "Unique address identifier", example = "10")
        Long id,
        @Schema(description = "Address street name", example = "Paulista Avenue")
        String street,
        @Schema(description = "Address city", example = "Sao Paulo")
        String city,
        @Schema(description = "Address number", example = "1000")
        String number,
        @Schema(description = "Address complement", example = "Suite 12")
        String complement,
        @Schema(description = "Address state", example = "SP")
        String state,
        @Schema(description = "Address ZIP code", example = "01310-100")
        String zipCode,
        @Schema(description = "Address country", example = "Brazil")
        String country
) {
    public static UserAddressResponseDTO fromEntity(UserAddress address) {
        return new UserAddressResponseDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getNumber(),
                address.getComplement(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
        );
    }
}