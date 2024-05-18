package org.authenticationservice.www.service;

import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.config.KeyStoreConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;

@Service
@Slf4j
public class KeyStoreService {
    private KeyStore keyStore;
    private KeyStoreConfigurationProperties keyStoreConfigurationProperties;

    public KeyStoreService(KeyStoreConfigurationProperties keyStoreConfigurationProperties) throws Exception {
        this.keyStoreConfigurationProperties = keyStoreConfigurationProperties;

        keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream keyStoreData = new FileInputStream(this.keyStoreConfigurationProperties.getPath())) {
            keyStore.load(keyStoreData, this.keyStoreConfigurationProperties.getPassword().toCharArray());
        }
    }

    public PrivateKey getPrivateKey(String alias) {
        //Проверка псевдонима
        try {
            return (PrivateKey) keyStore.getKey(alias, keyStoreConfigurationProperties.getEntriesPassword().toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey(String alias) {
        //Проверка псевдонима
        try {
            return keyStore.getCertificate(alias).getPublicKey();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
