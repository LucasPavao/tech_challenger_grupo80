package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import br.com.tech.challenger.api_restaurante.dto.v1.AuthenticationRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserResponseDTO;
import br.com.tech.challenger.api_restaurante.service.AuthenticationService;
import br.com.tech.challenger.api_restaurante.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiV1("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public void login(@RequestBody @Valid AuthenticationRequestDTO dto) {
        return;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }
}
