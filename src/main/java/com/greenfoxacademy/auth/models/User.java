package com.greenfoxacademy.auth.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Field: \"email\" is missing.")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Field: \"password\" is missing.")
    private String password;
    @NotNull(message = "Field: \"role\" is missing.")
    @ManyToMany
    private Collection<Role> roles;
    private Boolean activated;

    public User() {
    }

    public User(String email, String password, Collection <Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activated = false;
    }

    public User(@NotNull(message = "Field: \"email\" is missing.") @Email(message = "Invalid email address") String email, @NotNull(message = "Field: \"password\" is missing.") String password, Collection<Role> roles, Boolean activated) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activated = activated;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

