package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.app.factories.WebhookFactory;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.models.Webhook;
import com.intopays.sdk.infra.config.Environment;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebhookServiceTest {
	private WebhookService webhookService;
	private Webhook createdWebhook; 
	
    @BeforeEach
    public void setUp() {
    	IntopaysConstructor config = new IntopaysConstructor(Environment.get(EnvironmentTypeEnum.TEST).getToken(), EnvironmentTypeEnum.TEST);
        webhookService = WebhookFactory.createWebhookService(config);
    }

    @Test
    @Order(1)
    public void shouldCreateWebhookSuccessfully() throws Exception {
        // Arrange
        String endpoint = "https://intopays.com/" + UUID.randomUUID();
        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setEndpoint(endpoint);

        // Act
        Webhook reveived = this.webhookService.create(expectedWebhook);
        this.createdWebhook = reveived;
        // Assert
        assertEquals(endpoint, reveived.getEndpoint());
        assertNotNull(reveived.getSignature());
    }
    
    @Test
    @Order(2)
    public void shouldFindWebhooksSuccessfully() throws Exception {
        List<Webhook> webhooks = webhookService.search(this.createdWebhook);
        assertFalse(webhooks.isEmpty());
        assertTrue(webhooks.stream().anyMatch(w -> this.createdWebhook.getEndpoint().equals(w.getEndpoint())));
    }

    
    @Test
    @Order(3)
    public void shouldVerifyValidSignatureSuccessfully() throws Exception {
        String secret = "test-secret"; // replace with your actual test secret if needed

        // Generate signature manually
        javax.crypto.Mac sha256Hmac = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
        sha256Hmac.init(keySpec);
        byte[] hashBytes = sha256Hmac.doFinal(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(this.createdWebhook).getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        boolean result = webhookService.verifySignature(this.createdWebhook, hexString.toString(), secret);
        assertTrue(result);
    }
    
    @Test
    @Order(4)
    public void shouldFailVerificationWithInvalidSignature() {
        boolean result = webhookService.verifySignature(this.createdWebhook, "invalidsignature", "secret");
        assertFalse(result);
    }
    
    
    @Test
    @Order(5)
    public void shouldDeleteWebhookSuccessfully() throws Exception {
        Boolean deleted = this.webhookService.delete(String.valueOf(this.createdWebhook.getId()));
        assertEquals(deleted, true);
    }
}

