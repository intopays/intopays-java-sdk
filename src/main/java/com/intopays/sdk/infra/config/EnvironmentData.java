package com.intopays.sdk.infra.config;

public class EnvironmentData {
    private String host;
    private String token;

    public EnvironmentData(String host, String token) {
        this.host = host;
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public String getToken() {
        return token;
    }
}
