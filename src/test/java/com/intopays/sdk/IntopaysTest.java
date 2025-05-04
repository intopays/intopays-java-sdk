package com.intopays.sdk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.services.WebhookService;

public class IntopaysTest {
    
    @Test
    public void testIntopaysInitializesWebhookService() {
        // Arrange
        IntopaysConstructor config = new IntopaysConstructor("dummy-token", EnvironmentTypeEnum.TEST);
        // Act
        Intopays intopays = new Intopays(config);

        // Assert
        assertNotNull(intopays.webhook, "WebhookService should be initialized");
        assertTrue(intopays.webhook instanceof WebhookService, "webhook should be an instance of WebhookService");
    }
}
