package com.intopays.sdk.core.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa um webhook configurado para receber notificações de eventos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Webhook {

    /**
     * Identificador único do webhook.
     */
    private int id;

    /**
     * URL de destino onde os eventos serão enviados via HTTP POST.
     */
    private String endpoint;

    /**
     * Assinatura secreta usada para validar a autenticidade das requisições recebidas.
     * Normalmente utilizada para verificar a integridade do payload no recebimento do webhook.
     */
    private String signature;

    /**
     * Data de criação do webhook.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date createdAt;

    /**
     * Data de atualização do webhook.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date updatedAt;
    
    public Webhook() {
    	
    }
    
    public Webhook(Webhook data) {
        if (data != null) {
            this.id = data.id;
            this.endpoint = data.endpoint;
            this.signature = data.signature;
        }
    }

    /**
     * Obtém o identificador único do webhook.
     *
     * @return O identificador único do webhook.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do webhook.
     *
     * @param id O identificador único do webhook.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a URL de destino onde os eventos serão enviados via HTTP POST.
     *
     * @return A URL de destino onde os eventos serão enviados.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Define a URL de destino onde os eventos serão enviados via HTTP POST.
     *
     * @param endpoint A URL de destino onde os eventos serão enviados.
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Obtém a assinatura secreta usada para validar a autenticidade das requisições recebidas.
     *
     * @return A assinatura secreta usada para validar as requisições.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Define a assinatura secreta usada para validar a autenticidade das requisições recebidas.
     *
     * @param signature A assinatura secreta usada para validar as requisições.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Retorna uma representação em string do objeto Webhook.
     *
     * @return Uma string representando o Webhook.
     */
    @Override
    public String toString() {
        return "Webhook { " +
                "id=" + id +
                ", endpoint='" + endpoint + '\'' +
                ", signature='" + signature + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                " }";
    }
}