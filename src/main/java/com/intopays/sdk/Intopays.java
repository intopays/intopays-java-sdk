package com.intopays.sdk;

import com.intopays.sdk.app.factories.BoletoFactory;
import com.intopays.sdk.app.factories.PixFactory;
import com.intopays.sdk.app.factories.WebhookFactory;
import com.intopays.sdk.core.services.BoletoService;
import com.intopays.sdk.core.services.PixService;
import com.intopays.sdk.core.services.WebhookService;

/**
 * Classe principal para acessar os serviços da SDK Intopays.
 * Inicializa os serviços Pix, Boleto e Webhook com base na configuração fornecida.
 */
public class Intopays {

    /**
     * Serviço responsável pelas operações com Pix.
     */
    public final PixService pix;

    /**
     * Serviço responsável pelas operações com boletos.
     */
    public final BoletoService boleto;

    /**
     * Serviço responsável pelo gerenciamento de webhooks.
     */
    public final WebhookService webhook;

    /**
     * Construtor da classe Intopays.
     *
     * @param config Objeto de configuração usado para inicializar os serviços.
     */
    public Intopays(IntopaysConstructor config) {
        this.pix = PixFactory.createPixService(config);
        this.boleto = BoletoFactory.createBoletoService(config);
        this.webhook = WebhookFactory.createWebhookService(config);
    }

    /** @return Instância do serviço Pix */
    public PixService getPix() {
        return this.pix;
    }

    /** @return Instância do serviço Boleto */
    public BoletoService getBoleto() {
        return this.boleto;
    }

    /** @return Instância do serviço Webhook */
    public WebhookService getWebhook() {
        return this.webhook;
    }
}
