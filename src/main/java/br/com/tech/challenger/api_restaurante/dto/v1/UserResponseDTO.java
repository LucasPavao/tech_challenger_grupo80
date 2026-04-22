package br.com.tech.challenger.api_restaurante.dto.v1;

import br.com.tech.challenger.api_restaurante.entity.User;
import br.com.tech.challenger.api_restaurante.enums.UserType;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String login,
        UserAddressResponseDTO address,
        UserType userType,
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