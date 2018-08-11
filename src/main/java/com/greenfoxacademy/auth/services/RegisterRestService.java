package com.greenfoxacademy.auth.services;

import com.greenfoxacademy.auth.models.ErrorMessage;
import com.greenfoxacademy.auth.models.User;

import java.util.Collection;

public interface RegisterRestService {
    public User createNewUser(String email, String password);
    public boolean checkPassword(String password);
    public ErrorMessage weakPassword();
    public void updateUser(User actualUser);

    boolean checkIfEmailExists(String email);

    ErrorMessage emailAlreadyExists();
}

