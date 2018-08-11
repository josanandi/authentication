package com.greenfoxacademy.auth.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


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
    private Boolean activated;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.activated = false;
    }

    public User(@NotNull(message = "Field: \"email\" is missing.") @Email(message = "Invalid email address") String email, @NotNull(message = "Field: \"password\" is missing.") String password, Boolean activated) {
        this.email = email;
        this.password = password;
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

}

