package br.com.tech.challenger.api_restaurante.repository;

import br.com.tech.challenger.api_restaurante.entity.UserAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdressRepository extends JpaRepository<UserAdress, Long> {
}