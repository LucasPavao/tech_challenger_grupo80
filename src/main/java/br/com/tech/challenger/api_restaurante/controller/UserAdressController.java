package br.com.tech.challenger.api_restaurante.controller;

import br.com.tech.challenger.api_restaurante.service.UserAdressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.tech.challenger.api_restaurante.dto.UserAdressRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.UserAdressResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/v1/addresses")
@RequiredArgsConstructor
public class UserAdressController {

    private final UserAdressService userAdressService;

    @PostMapping
    public ResponseEntity<UserAdressResponseDTO> create(@RequestBody @Valid UserAdressRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAdressService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserAdressResponseDTO>> findAll() {
        return ResponseEntity.ok(userAdressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAdressResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userAdressService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAdressResponseDTO> update(@PathVariable Long id,
                                                        @RequestBody @Valid UserAdressRequestDTO dto) {
        return ResponseEntity.ok(userAdressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAdressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}