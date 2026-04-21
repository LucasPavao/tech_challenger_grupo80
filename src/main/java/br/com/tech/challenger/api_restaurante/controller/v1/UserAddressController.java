package br.com.tech.challenger.api_restaurante.controller.v1;

import br.com.tech.challenger.api_restaurante.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.tech.challenger.api_restaurante.dto.v1.UserAddressRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserAddressResponseDTO;

import java.util.List;

@ApiV1("/addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @PostMapping
    public ResponseEntity<UserAddressResponseDTO> create(@RequestBody @Valid UserAddressRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAddressService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserAddressResponseDTO>> findAll() {
        return ResponseEntity.ok(userAddressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userAddressService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddressResponseDTO> update(@PathVariable Long id,
                                                         @RequestBody @Valid UserAddressRequestDTO dto) {
        return ResponseEntity.ok(userAddressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}