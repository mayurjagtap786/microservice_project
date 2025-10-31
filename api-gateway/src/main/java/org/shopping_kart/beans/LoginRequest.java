package com.example.restwebservice.beans;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String secrets;

}
