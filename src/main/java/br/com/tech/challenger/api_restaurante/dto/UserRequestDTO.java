package br.com.tech.challenger.api_restaurante.dto;

import br.com.tech.challenger.api_restaurante.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Login é obrigatório")
        String login,

        @NotBlank(message = "Senha é obrigatória")
        String password,

        String address,

        @NotNull(message = "Tipo de usuário é obrigatório")
        UserType userType
) {}