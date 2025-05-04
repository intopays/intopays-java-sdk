package com.intopays.sdk.core.services;

import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intopays.sdk.core.models.Webhook;
import com.intopays.sdk.infra.http.WebhookRemote;
import com.intopays.sdk.infra.http.request.WebhookCreateData;

public class WebhookService {
    private final WebhookRemote remote;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WebhookService(WebhookRemote remote) {
        this.remote = remote;
    }

    public Webhook create(Webhook data) throws Exception {
        WebhookCreateData webhook = new WebhookCreateData();
        webhook.setEndpoint(data.getEndpoint());
        return this.remote.create(webhook);
    }

    public List<Webhook> find(Webhook data) throws Exception {
        return this.remote.find(data != null ? this.objectMapper.convertValue(data, Map.class) : null);
    }

    public Webhook delete(int id) throws Exception {
        return this.remote.delete(id);
    }

    public boolean verifySignature(Object payload, String xWebhookSignature, String secretKey) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);
            byte[] hashBytes = sha256Hmac.doFinal(jsonPayload.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return xWebhookSignature.equals(hexString.toString());
        } catch (Exception e) {
            return false;
        }
    }
}

