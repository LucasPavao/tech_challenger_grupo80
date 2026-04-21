package br.com.tech.challenger.api_restaurante.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record UserAddressRequestDTO(

        @NotBlank(message = "Rua é obrigatória")
        String street,

        @NotBlank(message = "Cidade é obrigatória")
        String city,

        String number,

        String complement,

        @NotBlank(message = "Estado é obrigatório")
        String state,

        @NotBlank(message = "CEP é obrigatório")
        String zipCode,

        @NotBlank(message = "País é obrigatório")
        String country
) {}