package com.intopays.sdk.core.services;

import java.util.HashMap;
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

    public List<Webhook> search(Webhook data) throws Exception {
    	Map<String, String> queryParams = new HashMap<String, String>();

    	if (data.getEndpoint() != null) {
    		queryParams.put("endpoint", data.getEndpoint());
    	}
    	if (data.getSignature() != null) {
        	queryParams.put("signature", data.getSignature());
    	}

        return this.remote.search(queryParams);
    }

    public Boolean delete(String id) throws Exception {
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

