package com.greenfoxacademy.auth.controllers;
import com.greenfoxacademy.auth.models.ErrorMessage;
import com.greenfoxacademy.auth.models.User;
import com.greenfoxacademy.auth.services.RegisterRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegisterRestController extends ResponseEntityExceptionHandler {

    RegisterRestService registerRestService;


    @Autowired
    public RegisterRestController(RegisterRestService registerRestService) {
        this.registerRestService = registerRestService;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("api/user")
    public ResponseEntity<Object> registration(@Valid @RequestBody(required = false) User user){
        if (registerRestService.checkIfEmailExists(user.getEmail())){
            ErrorMessage emailAlreadyExists = registerRestService.emailAlreadyExists();
            return new ResponseEntity<>(emailAlreadyExists, HttpStatus.BAD_REQUEST);
        }
        else if (!registerRestService.checkPassword(user.getPassword())) {
            ErrorMessage weakPasswordUser = registerRestService.weakPassword();
            return new ResponseEntity<>(weakPasswordUser, HttpStatus.BAD_REQUEST);
        } else {
            User newUser = registerRestService.createNewUser(user.getEmail(), user.getPassword(), user.getRoles());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }


}