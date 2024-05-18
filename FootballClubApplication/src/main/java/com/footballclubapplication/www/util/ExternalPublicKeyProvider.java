package com.footballclubapplication.www.util;

import com.footballclubapplication.www.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class ExternalPublicKeyProvider implements PublicKeyProvider {
    private final AuthenticationService authenticationService;

    public ExternalPublicKeyProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public String getPublicKey(String kid) {
        return authenticationService.takePublicKey(kid).getPublicKey();
    }
}
