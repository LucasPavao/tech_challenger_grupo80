package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.dto.v1.UpdatePasswordRequestDTO;
import br.com.tech.challenger.api_restaurante.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.tech.challenger.api_restaurante.dto.v1.UserRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserResponseDTO;

import java.util.List;

@ApiV1("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findByName(@PathParam("name") String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/password/update")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,
                                               @RequestBody @Valid UpdatePasswordRequestDTO dto) {
        userService.updatePassword(id, dto);
        return ResponseEntity.ok().build();
    }
}