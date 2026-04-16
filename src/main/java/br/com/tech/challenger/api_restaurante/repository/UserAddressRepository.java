package br.com.tech.challenger.api_restaurante.repository;

import br.com.tech.challenger.api_restaurante.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}