package br.com.tech.challenger.api_restaurante.repository;

import br.com.tech.challenger.api_restaurante.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByName(String name);

    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByLoginAndPassword(String login, String password);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

}

