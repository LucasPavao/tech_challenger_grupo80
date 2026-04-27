package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import br.com.tech.challenger.api_restaurante.dto.v1.*;
import br.com.tech.challenger.api_restaurante.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiV1("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and access token endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Validates credentials and returns user data with access and refresh tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<AuthenticatedUserResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(dto));
    }

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Creates a new user account and returns authentication tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<AuthenticatedUserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(dto));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh access token", description = "Generates a new access token from a valid refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<AuthenticationTokenDetailsDTO> refreshToken(@RequestBody @Valid AuthenticationRefreshTokenRequestDto dto) {
        return ResponseEntity.status((HttpStatus.OK)).body(authenticationService.refreshToken(dto));
    }
}
