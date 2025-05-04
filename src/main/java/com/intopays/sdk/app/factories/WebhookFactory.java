package com.intopays.sdk.app.factories;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.services.WebhookService;
import com.intopays.sdk.infra.http.WebhookRemote;

/**
 * Fábrica responsável por criar instâncias do serviço de Webhook.
 */
public class WebhookFactory {

    /**
     * Cria uma instância do serviço de Webhook.
     *
     * @param config Configuração da instância Intopays.
     * @return Uma nova instância de WebhookService.
     */
    public static WebhookService createWebhookService(IntopaysConstructor config) {
        return new WebhookService(new WebhookRemote(config));
    }
}
