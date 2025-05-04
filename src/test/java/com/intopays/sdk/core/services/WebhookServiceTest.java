package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.app.factories.WebhookFactory;
import com.intopays.sdk.core.models.Webhook;
public class WebhookServiceTest {
	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NiwiZW1haWwiOiJzZGtAaW50b3BheXMuY29tIiwiaWF0IjoxNzEzMTMzMDMwfQ.0uOXAMSq09aasfUkDCzuvaKVUBBAZf0mU1uBz-UDXkQ";
    private WebhookService webhookService;

    @BeforeEach
    public void setUp() {
        // Mock the remote dependency
    	IntopaysConstructor config = new IntopaysConstructor(this.TOKEN, "development");
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
}

