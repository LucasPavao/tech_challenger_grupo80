package br.com.tech.challenger.api_restaurante.repository;

import br.com.tech.challenger.api_restaurante.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

