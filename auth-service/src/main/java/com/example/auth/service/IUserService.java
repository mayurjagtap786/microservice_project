package com.example.auth.service;


import com.example.auth.beans.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    public Optional<User> getUser(int id);

    public List<User> getAllUser();

    public User saveUser(User user);

    public void deleteUserById(int id);

}
