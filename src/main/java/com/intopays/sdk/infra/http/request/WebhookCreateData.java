package com.intopays.sdk.infra.http.request;

public class WebhookCreateData {
    private String endpoint;

    public WebhookCreateData() {}

    public WebhookCreateData(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}