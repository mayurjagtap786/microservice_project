package com.example.auth.controller;



import com.example.auth.beans.User;
import com.example.auth.exception.UserNotFoundException;
import com.example.auth.serviceimpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public User greetUser(@PathVariable int id){
        return  userService.getUser(id).orElseThrow( () -> new UserNotFoundException("User Not found"));
    }

    @GetMapping("/")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        log.info("request Body {}",user);
        //log.info("Header Content Type {}",headers.getContentType());
       User savedUser =  userService.saveUser(user);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{/id}")
                        .buildAndExpand(savedUser.getUserID())
                        .toUri();
         return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUserById(id);
    }
}