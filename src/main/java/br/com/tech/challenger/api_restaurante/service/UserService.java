package br.com.tech.challenger.api_restaurante.service;

import br.com.tech.challenger.api_restaurante.dto.v1.UpdatePasswordRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserRequestDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserResponseDTO;
import br.com.tech.challenger.api_restaurante.entity.User;
import br.com.tech.challenger.api_restaurante.entity.UserAddress;
import br.com.tech.challenger.api_restaurante.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO create(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já cadastrado: " + dto.email());
        }
        if (userRepository.existsByLogin(dto.login())) {
            throw new IllegalArgumentException("Login já cadastrado: " + dto.login());
        }

        UserAddress address = new UserAddress();
        address.setCity(dto.address().city());
        address.setStreet(dto.address().street());
        address.setState(dto.address().state());
        address.setNumber(dto.address().number());
        address.setCountry(dto.address().country());
        address.setComplement(dto.address().complement());
        address.setZipCode(dto.address().zipCode());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUserType(dto.userType());
        user.setUserAddress(address);

        return UserResponseDTO.fromEntity(userRepository.save(user));
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public List<UserResponseDTO> findByName(String name) {
         return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public Optional<UserResponseDTO> findByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(UserResponseDTO::fromEntity);
    }

    public Optional<User> findEntityByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        if (!user.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já cadastrado: " + dto.email());
        }
        if (!user.getLogin().equals(dto.login()) && userRepository.existsByLogin(dto.login())) {
            throw new IllegalArgumentException("Login já cadastrado: " + dto.login());
        }

        UserAddress address = user.getUserAddress();
        address.setCity(dto.address().city());
        address.setStreet(dto.address().street());
        address.setState(dto.address().state());
        address.setNumber(dto.address().number());
        address.setCountry(dto.address().country());
        address.setComplement(dto.address().complement());
        address.setZipCode(dto.address().zipCode());

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setUserAddress(address);
        user.setUserType(dto.userType());

        return UserResponseDTO.fromEntity(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado: " + id);
        }
        userRepository.deleteById(id);
    }

    public void updatePassword(Long id, UpdatePasswordRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        user.setPassword(passwordEncoder.encode(dto.newPassword()));

        UserResponseDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}