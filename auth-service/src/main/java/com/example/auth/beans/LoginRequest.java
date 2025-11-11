package com.example.auth.beans;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String secrets;

}
