package org.authenticationservice.www.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicKeyDTO {
    private String publicKey;
}
