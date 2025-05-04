package com.intopays.sdk;

public class Intopays {
    private final String token;
    private final String mode;

    public Intopays(IntopaysConstructor config) {
        this.token = config.token;
        this.mode = config.mode;
    }
}
