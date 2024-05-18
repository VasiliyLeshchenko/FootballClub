package com.footballclubapplication.www.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "public-key-provider")
public class PublicKeyProviderProperties {
    private boolean local;
}
