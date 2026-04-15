package br.com.tech.challenger.api_restaurante.dto;

import br.com.tech.challenger.api_restaurante.entity.UserAdress;

public record UserAdressResponseDTO(
        Long id,
        String street,
        String city,
        String number,
        String complement,
        String state,
        String zipCode,
        String country
) {
    public static UserAdressResponseDTO fromEntity(UserAdress adress) {
        return new UserAdressResponseDTO(
                adress.getId(),
                adress.getStreet(),
                adress.getCity(),
                adress.getNumber(),
                adress.getComplement(),
                adress.getState(),
                adress.getZipCode(),
                adress.getCountry()
        );
    }
}