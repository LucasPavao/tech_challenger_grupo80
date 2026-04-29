package br.com.tech.challenger.api_restaurante.service;

import br.com.tech.challenger.api_restaurante.dto.v1.UserAddressRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserAddressResponseDTO;
import br.com.tech.challenger.api_restaurante.entity.UserAddress;
import br.com.tech.challenger.api_restaurante.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddressResponseDTO create(UserAddressRequestDTO dto) {
        UserAddress address = new UserAddress();
        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setState(dto.state());
        address.setZipCode(dto.zipCode());
        address.setCountry(dto.country());

        return UserAddressResponseDTO.fromEntity(userAddressRepository.save(address));
    }

    public List<UserAddressResponseDTO> findAll() {
        return userAddressRepository.findAll()
                .stream()
                .map(UserAddressResponseDTO::fromEntity)
                .toList();
    }

    public UserAddressResponseDTO findById(Long id) {
        return userAddressRepository.findById(id)
                .map(UserAddressResponseDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado: " + id));
    }

    public UserAddressResponseDTO update(Long id, UserAddressRequestDTO dto) {
        UserAddress address = userAddressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado: " + id));

        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setState(dto.state());
        address.setZipCode(dto.zipCode());
        address.setCountry(dto.country());

        return UserAddressResponseDTO.fromEntity(userAddressRepository.save(address));
    }

    public void delete(Long id) {
        if (!userAddressRepository.existsById(id)) {
            throw new IllegalArgumentException("Endereço não encontrado: " + id);
        }
        userAddressRepository.deleteById(id);
    }
}