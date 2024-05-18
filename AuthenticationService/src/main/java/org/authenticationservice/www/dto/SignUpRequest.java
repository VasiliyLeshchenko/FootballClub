package org.authenticationservice.www.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String login;
    private String email;
    private String password;
}