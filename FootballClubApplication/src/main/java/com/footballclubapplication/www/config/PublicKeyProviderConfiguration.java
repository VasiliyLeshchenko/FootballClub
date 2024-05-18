package com.footballclubapplication.www.config;

import com.footballclubapplication.www.service.AuthenticationService;
import com.footballclubapplication.www.util.ExternalPublicKeyProvider;
import com.footballclubapplication.www.util.LocalPublicKeyProvider;
import com.footballclubapplication.www.util.PublicKeyProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicKeyProviderConfiguration {
    @Bean
    public PublicKeyProvider publicKeyProvider(PublicKeyProviderProperties publicKeyProviderProperties, AuthenticationService authenticationService, LocalPublicKeys localPublicKeys) {
        if (publicKeyProviderProperties.isLocal()) {
            return new LocalPublicKeyProvider(localPublicKeys.getPublicKeys());
        } else {
            return new ExternalPublicKeyProvider(authenticationService);
        }
    }
}
