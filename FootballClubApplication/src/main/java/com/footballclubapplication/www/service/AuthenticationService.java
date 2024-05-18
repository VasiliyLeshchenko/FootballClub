package com.footballclubapplication.www.service;

import com.footballclubapplication.www.config.AuthenticationServiceConfigurationProperties;
import com.footballclubapplication.www.dto.PublicKeyDTO;
import com.footballclubapplication.www.dto.RolesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthenticationServiceConfigurationProperties serviceConfig;

    public PublicKeyDTO takePublicKey(String alias) {
        RequestEntity request = RequestEntity
                .get(URI.create("http://" + serviceConfig.getHost() + "/auth/public-key/" + alias))
                .build();
        try {
            return restTemplate.exchange(request, PublicKeyDTO.class).getBody();
        } catch (ResourceAccessException e) {
            log.error("Error while getting public key", e);
            throw new RuntimeException(e);
        }
    }

    public RolesDTO getUserRoles(Long id) {
        RequestEntity request = RequestEntity
                .get(URI.create("http://" + serviceConfig.getHost() + "/users/" + id + "/roles"))
                .build();
        return restTemplate.exchange(request, RolesDTO.class).getBody();
    }
}
