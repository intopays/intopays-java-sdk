package com.intopays.sdk.app.factories;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.services.BoletoService;
import com.intopays.sdk.infra.http.BoletoRemote;

/**
 * Fábrica responsável por criar instâncias do serviço de boletos.
 */
public class BoletoFactory {

    /**
     * Cria uma instância de BoletoService com a configuração fornecida.
     *
     * @param config Configuração usada para inicializar a integração.
     * @return Instância de BoletoService.
     */
    public static BoletoService createBoletoService(IntopaysConstructor config) {
        return new BoletoService(new BoletoRemote(config));
    }
}
