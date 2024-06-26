package org.authenticationservice.www.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "key-store")
public class KeyStoreConfigurationProperties {
    private String path;
    private String password;
    private String entriesPassword;
}
