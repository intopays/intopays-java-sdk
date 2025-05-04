package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.app.factories.WebhookFactory;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.models.Webhook;
import com.intopays.sdk.infra.config.Environment;

public class WebhookServiceTest {
	private WebhookService webhookService;
	
    @BeforeEach
    public void setUp() {
        // Mock the remote dependency
    	IntopaysConstructor config = new IntopaysConstructor(Environment.get(EnvironmentTypeEnum.TEST).getToken(), EnvironmentTypeEnum.TEST.getValue());
        webhookService = WebhookFactory.createWebhookService(config);
    }

    @Test
    public void shouldCreateWebhookSuccessfully() throws Exception {
        // Arrange
        String endpoint = "https://intopays.com/" + UUID.randomUUID();
        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setEndpoint(endpoint);

        // Act
        Webhook reveived = this.webhookService.create(expectedWebhook);
        System.out.println(reveived);
        // Assert
        assertEquals(endpoint, reveived.getEndpoint());
        assertNotNull(reveived.getSignature());
    }
    
    @Test
    public void shouldFindWebhooksSuccessfully() throws Exception {
        Webhook webhook = new Webhook();
        String endpoint = "https://intopays.com/" + UUID.randomUUID();
        webhook.setEndpoint(endpoint);
        Webhook created = webhookService.create(webhook);

        Map<String, String> filter = new HashMap<>();
        filter.put("endpoint", endpoint);

        List<Webhook> webhooks = webhookService.search(webhook);

        assertFalse(webhooks.isEmpty());
        assertTrue(webhooks.stream().anyMatch(w -> endpoint.equals(w.getEndpoint())));
    }
    
    @Test
    public void shouldDeleteWebhookSuccessfully() throws Exception {
        Webhook webhook = new Webhook();
        webhook.setEndpoint("https://intopays.com/" + UUID.randomUUID());
        Webhook created = this.webhookService.create(webhook);

        Boolean deleted = this.webhookService.delete(String.valueOf(created.getId()));
        assertEquals(deleted, true);
    }
    
    @Test
    public void shouldVerifyValidSignatureSuccessfully() throws Exception {
        Webhook payload = new Webhook();
        payload.setEndpoint("https://intopays.com/" + UUID.randomUUID());

        String secret = "test-secret"; // replace with your actual test secret if needed

        // Generate signature manually
        javax.crypto.Mac sha256Hmac = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
        sha256Hmac.init(keySpec);
        byte[] hashBytes = sha256Hmac.doFinal(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(payload).getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        boolean result = webhookService.verifySignature(payload, hexString.toString(), secret);
        assertTrue(result);
    }
    
    @Test
    public void shouldFailVerificationWithInvalidSignature() {
        Webhook payload = new Webhook();
        payload.setEndpoint("https://intopays.com/");

        boolean result = webhookService.verifySignature(payload, "invalidsignature", "secret");
        assertFalse(result);
    }
}

