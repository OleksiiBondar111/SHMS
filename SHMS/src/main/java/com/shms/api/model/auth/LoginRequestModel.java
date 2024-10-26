package com.shms.api.model.auth;


import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
