package com.footballclubapplication.www.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.FileUrlResource;

import java.util.Map;
import java.util.Properties;


@Getter
@Setter
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "local-keys")
public class LocalPublicKeys {
    private Map<String, String> publicKeys;

}
