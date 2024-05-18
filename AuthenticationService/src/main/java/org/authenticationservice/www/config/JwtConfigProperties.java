package org.authenticationservice.www.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {
//    private String privateKey;
//    private String publicKey;
    private Long expirationTime;
}
