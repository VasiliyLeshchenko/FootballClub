package com.footballclubapplication.www.util;

import java.util.Map;

public class LocalPublicKeyProvider implements PublicKeyProvider {
    private Map<String, String> publicKeys;

    public LocalPublicKeyProvider(Map<String, String> publicKeys) {
        this.publicKeys = publicKeys;
    }

    @Override
    public String getPublicKey(String kid) {
        return publicKeys.get(kid);
    }
}
