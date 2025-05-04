package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

