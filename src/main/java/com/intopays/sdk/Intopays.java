package com.intopays.sdk;

import com.intopays.sdk.app.factories.WebhookFactory;
import com.intopays.sdk.core.services.WebhookService;

public class Intopays {
    public final WebhookService webhook;
  
    public Intopays(IntopaysConstructor config) {
        this.webhook = WebhookFactory.createWebhookService(config);
    }
}
