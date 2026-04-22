package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import br.com.tech.challenger.api_restaurante.dto.v1.*;
import br.com.tech.challenger.api_restaurante.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiV1("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticatedUserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(dto));
    }
}
