package com.intopays.sdk;

import com.intopays.sdk.core.services.WebhookService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntopaysTest {

    @Test
    public void testIntopaysInitializesWebhookService() {
        // Arrange
        IntopaysConstructor config = new IntopaysConstructor("dummy-token", "development");
        // Act
        Intopays intopays = new Intopays(config);

        // Assert
        assertNotNull(intopays.webhook, "WebhookService should be initialized");
        assertTrue(intopays.webhook instanceof WebhookService, "webhook should be an instance of WebhookService");
    }
}
