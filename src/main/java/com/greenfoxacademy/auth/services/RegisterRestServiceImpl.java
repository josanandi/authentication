package com.greenfoxacademy.auth.services;

import com.greenfoxacademy.auth.models.ErrorMessage;
import com.greenfoxacademy.auth.models.User;
import com.greenfoxacademy.auth.repositories.UserRepository;
import com.greenfoxacademy.auth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class RegisterRestServiceImpl implements RegisterRestService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public RegisterRestServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {

        this.userRepository = userRepository;
        this.jwtTokenProvider =jwtTokenProvider;
    }


    @Override
    public User createNewUser(String email, String password) {
        User newUser = new User(email, password, false);
        String token = jwtTokenProvider.createToken(email);
        System.out.println(token);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public boolean checkPassword(String password) {
        if (password.length() >= 8 && !password.equals(password.toLowerCase())
                && !password.equals(password.toUpperCase()) && password.matches(".*\\d+.*")) {
            return true;
        }
        return false;
    }

    @Override
    public ErrorMessage weakPassword() {
        return new ErrorMessage(
                "Password is not strong enough. It should have at least 8 characters. It should contain lowercase, uppercase letters and numbers.");
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ErrorMessage emailAlreadyExists() {
        return new ErrorMessage("There is already an account with this email address in our database");
    }

    @Override
    public void updateUser(User actualUser) {
        actualUser.setActivated(true);
        userRepository.save(actualUser);
    }


}
