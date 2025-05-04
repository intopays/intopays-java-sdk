package com.intopays.sdk.app.factories;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.services.PixService;
import com.intopays.sdk.infra.http.PixRemote;

/**
 * Fábrica responsável pela criação do serviço de Pix.
 */
public class PixFactory {

    /**
     * Cria uma instância do serviço PixService.
     *
     * @param config Configuração necessária para inicializar a comunicação com o servidor.
     * @return Uma instância de PixService configurada.
     */
    public static PixService createPixService(IntopaysConstructor config) {
        return new PixService(new PixRemote(config));
    }
}

