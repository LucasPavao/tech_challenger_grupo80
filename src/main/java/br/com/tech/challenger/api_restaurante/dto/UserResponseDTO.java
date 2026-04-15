package br.com.tech.challenger.api_restaurante.dto;

import br.com.tech.challenger.api_restaurante.entity.User;
import br.com.tech.challenger.api_restaurante.enums.UserType;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String login,
        String address,
        UserType userType,
        LocalDateTime lastModifiedDate
) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getAddress(),
                user.getUserType(),
                user.getLastModifiedDate()
        );
    }
}