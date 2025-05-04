package com.intopays.sdk;

public class IntopaysConstructor {
    private String token;
    private String mode;

    public IntopaysConstructor(String token, String mode) {
        this.token = token;
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public String getMode() {
        return mode;
    }
}
