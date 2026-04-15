package br.com.tech.challenger.api_restaurante.service;

import br.com.tech.challenger.api_restaurante.dto.UserAdressRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.UserAdressResponseDTO;
import br.com.tech.challenger.api_restaurante.entity.UserAdress;
import br.com.tech.challenger.api_restaurante.repository.UserAdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdressService {

    private final UserAdressRepository userAdressRepository;

    public UserAdressResponseDTO create(UserAdressRequestDTO dto) {
        UserAdress adress = new UserAdress();
        adress.setStreet(dto.street());
        adress.setCity(dto.city());
        adress.setNumber(dto.number());
        adress.setComplement(dto.complement());
        adress.setState(dto.state());
        adress.setZipCode(dto.zipCode());
        adress.setCountry(dto.country());

        return UserAdressResponseDTO.fromEntity(userAdressRepository.save(adress));
    }

    public List<UserAdressResponseDTO> findAll() {
        return userAdressRepository.findAll()
                .stream()
                .map(UserAdressResponseDTO::fromEntity)
                .toList();
    }

    public UserAdressResponseDTO findById(Long id) {
        return userAdressRepository.findById(id)
                .map(UserAdressResponseDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado: " + id));
    }

    public UserAdressResponseDTO update(Long id, UserAdressRequestDTO dto) {
        UserAdress adress = userAdressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado: " + id));

        adress.setStreet(dto.street());
        adress.setCity(dto.city());
        adress.setNumber(dto.number());
        adress.setComplement(dto.complement());
        adress.setState(dto.state());
        adress.setZipCode(dto.zipCode());
        adress.setCountry(dto.country());

        return UserAdressResponseDTO.fromEntity(userAdressRepository.save(adress));
    }

    public void delete(Long id) {
        if (!userAdressRepository.existsById(id)) {
            throw new IllegalArgumentException("Endereço não encontrado: " + id);
        }
        userAdressRepository.deleteById(id);
    }
}