package br.com.tech.challenger.api_restaurante.service;

import br.com.tech.challenger.api_restaurante.entity.User;
import br.com.tech.challenger.api_restaurante.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecuryService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findEntityByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
