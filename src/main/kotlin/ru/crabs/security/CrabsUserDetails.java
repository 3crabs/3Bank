package ru.crabs.security;

import io.micronaut.security.authentication.UserDetails;

import java.util.Collection;

public class CrabsUserDetails extends UserDetails {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrabsUserDetails(Long id, String username, Collection<String> roles) {
        super(username, roles);
        this.id = id;
    }
}
