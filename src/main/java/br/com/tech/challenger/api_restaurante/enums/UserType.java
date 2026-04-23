package br.com.tech.challenger.api_restaurante.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum UserType {

    CUSTOMER(List.of("ROLE_CUSTOMER")),
    RESTAURANT_OWNER(List.of("ROLE_CUSTOMER", "ROLE_RESTAURANT_OWNER")),;

    private final List<String> roles;
    UserType(List<String> roles) { this.roles = roles; }
    public List<String> getRoles() { return roles; }
}
