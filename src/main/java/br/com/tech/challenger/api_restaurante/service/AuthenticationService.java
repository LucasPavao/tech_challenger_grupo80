package br.com.tech.challenger.api_restaurante.service;

import br.com.tech.challenger.api_restaurante.config.security.TokenService;
import br.com.tech.challenger.api_restaurante.dto.v1.*;
import br.com.tech.challenger.api_restaurante.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthenticatedUserResponseDTO login(AuthenticationRequestDTO dto){
        Authentication authentication = this.authenticate(dto);

        User user = (User) authentication.getPrincipal();
        AuthenticationTokenDetailsDTO tokenDetailsDTO = tokenService.generateToken(UserResponseDTO.fromEntity(user));
        return new AuthenticatedUserResponseDTO(
                UserResponseDTO.fromEntity(user),
                tokenDetailsDTO
        );
    }

    public AuthenticatedUserResponseDTO register(UserRequestDTO dto){
        userService.create(dto);
        Authentication authentication = this.authenticate(new AuthenticationRequestDTO(
                dto.login(),
                dto.password()
        ));

        User user = (User) authentication.getPrincipal();
        AuthenticationTokenDetailsDTO tokenDetailsDTO = tokenService.generateToken(UserResponseDTO.fromEntity(user));
        return new AuthenticatedUserResponseDTO(
                UserResponseDTO.fromEntity(user),
                tokenDetailsDTO
        );
    }

    public AuthenticationTokenDetailsDTO refreshToken(AuthenticationRefreshTokenRequestDto dto){
        Long userId = Long.valueOf(tokenService.validateToken(dto.refreshToken()));
        UserResponseDTO userDto = userService.findById(userId).orElseThrow();
        return tokenService.generateToken(userDto);
    }

    private Authentication authenticate(AuthenticationRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        return authenticationManager.authenticate(authenticationToken);
    }
}
