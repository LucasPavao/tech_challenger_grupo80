package br.com.tech.challenger.api_restaurante.dto.v1;

import br.com.tech.challenger.api_restaurante.entity.UserAddress;

public record UserAddressResponseDTO(
        Long id,
        String street,
        String city,
        String number,
        String complement,
        String state,
        String zipCode,
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