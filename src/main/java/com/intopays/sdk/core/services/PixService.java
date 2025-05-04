package com.intopays.sdk.core.services;

import com.intopays.sdk.core.models.Pix;
import com.intopays.sdk.infra.http.PixRemote;
import com.intopays.sdk.infra.http.response.PageResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço responsável pela lógica de negócios relacionada ao Pix.
 */
public class PixService {
    private final PixRemote remote;

    /**
     * Construtor da classe PixService.
     *
     * @param remote Instância da classe PixRemote responsável pela comunicação com o servidor.
     */
    public PixService(PixRemote remote) {
        this.remote = remote;
    }

    /**
     * Cria um novo objeto Pix no sistema.
     *
     * @param data Dados necessários para a criação do Pix.
     * @return O objeto Pix criado.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public Pix create(Pix data) throws IOException {
        return this.remote.create(data);
    }

    /**
     * Busca uma lista de objetos Pix com base nos dados fornecidos.
     *
     * @param data Objeto Pix contendo os dados de busca. Se algum campo estiver nulo, será ignorado na consulta (opcional).
     * @return Lista de objetos Pix que atendem aos critérios fornecidos.
     * @throws IOException Caso ocorra erro na requisição ou na comunicação com o servidor.
     */
    public List<Pix> search(Pix data) throws IOException {
    	Map<String, String> queryParams = new HashMap<String, String>();

        if (data.getTransactionId() != null && !data.getTransactionId().isEmpty()) {
            queryParams.put("transactionId", data.getTransactionId());
        }
        if (data.getPixKey() != null && !data.getPixKey().isEmpty()) {
            queryParams.put("pixKey", data.getPixKey());
        }
        if (data.getDebtorName() != null && !data.getDebtorName().isEmpty()) {
            queryParams.put("debtorName", data.getDebtorName());
        }
        if (data.getDebtorDocument() != null && !data.getDebtorDocument().isEmpty()) {
            queryParams.put("debtorDocument", data.getDebtorDocument());
        }
        if (data.getAmountOriginal() != null && !data.getAmountOriginal().isEmpty()) {
            queryParams.put("amountOriginal", data.getAmountOriginal());
        }
        if (data.getPayerRequest() != null && !data.getPayerRequest().isEmpty()) {
            queryParams.put("payerRequest", data.getPayerRequest());
        }
        if (data.getIntegrationType() != null) {
            queryParams.put("integrationType", data.getIntegrationType().name());
        }
        if (data.getStatus() != null) {
            queryParams.put("status", data.getStatus().name());
        }
        if (data.getRevision() != null) {
            queryParams.put("revision", String.valueOf(data.getRevision()));
        }
        if (data.getLocId() != null) {
            queryParams.put("locId", String.valueOf(data.getLocId()));
        }
        if (data.getLocLocation() != null && !data.getLocLocation().isEmpty()) {
            queryParams.put("locLocation", data.getLocLocation());
        }
        if (data.getLocType() != null && !data.getLocType().isEmpty()) {
            queryParams.put("locType", data.getLocType());
        }
        if (data.getLocation() != null && !data.getLocation().isEmpty()) {
            queryParams.put("location", data.getLocation());
        }
        if (data.getId() != null) {
            queryParams.put("id", String.valueOf(data.getId()));
        }
        if (data.getCalendarExpiration() > 0) {
            queryParams.put("calendarExpiration", String.valueOf(data.getCalendarExpiration()));
        }
        if (data.getAmountModificationType() > 0) {
            queryParams.put("amountModificationType", String.valueOf(data.getAmountModificationType()));
        }
        
        PageResponse<Pix> response = this.remote.search(queryParams);
        return response.getBody();
    }

    /**
     * Encontra um objeto Pix específico pelo seu ID.
     *
     * @param id ID do Pix a ser encontrado.
     * @return O objeto Pix encontrado.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public Pix find(String id) throws IOException {
        return this.remote.find(id);
    }
}
