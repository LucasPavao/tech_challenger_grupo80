package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.dto.v1.UpdatePasswordRequestDTO;
import br.com.tech.challenger.api_restaurante.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import br.com.tech.challenger.api_restaurante.dto.v1.UserRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserResponseDTO;

import java.util.List;

@ApiV1("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "List users", description = "Returns all users or filters by name when the 'name' query parameter is provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users returned successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<List<UserResponseDTO>> findAllFiltered(@PathParam("name") String name) {
        if(name == null) {
            return ResponseEntity.ok(userService.findAll());
        }
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates user profile data for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Operation not allowed for this user", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @PreAuthorize("#id == authentication.principal.id")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes the authenticated user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Operation not allowed for this user", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/{id}/password/update")
    @Operation(summary = "Update password", description = "Changes the password for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Operation not allowed for this user", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,
                                               @RequestBody @Valid UpdatePasswordRequestDTO dto) {
        userService.updatePassword(id, dto);
        return ResponseEntity.ok().build();
    }
}