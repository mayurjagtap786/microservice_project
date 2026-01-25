package com.example.auth.controller;


import com.example.auth.beans.LoginRequest;
import com.example.auth.exception.*;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws CredentialEmptyException {
            Map<HttpStatusCode, String> response = new HashMap<>();
            if(loginRequest.getUsername().isBlank() || loginRequest.getSecrets().isBlank()){
                throw new CredentialEmptyException("Username & Password should not be empty");
            }
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getSecrets())
        );
        if(authentication!=null){
            log.debug("User {} Login successfully done....",loginRequest.getUsername());
            response.put(HttpStatusCode.valueOf(200),"Login Successfully");
            return  ResponseEntity.ok(response);
        }else {
            log.error("User {} Login failed....",loginRequest.getUsername());
            throw new UserNotFoundException("user not exist");
        }
    }
}