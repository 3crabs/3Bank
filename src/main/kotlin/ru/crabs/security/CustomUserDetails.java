package ru.crabs.security;

import io.micronaut.security.authentication.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends UserDetails {

    private Long id;

    public CustomUserDetails(String username, Collection<String> roles, Long id) {
        super(username, roles);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
